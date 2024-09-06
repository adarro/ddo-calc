package io.truthencode.ddo.codex.rest;


import io.quarkiverse.renarde.router.Router;
import io.quarkiverse.renarde.security.ControllerWithUser;
import io.quarkiverse.renarde.util.StringUtils;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import io.truthencode.ddo.codex.model.User;
import io.truthencode.ddo.codex.model.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import org.hibernate.validator.constraints.Length;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.UUID;


public class Login extends ControllerWithUser<User> {

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance login();

        public static native TemplateInstance register(String email);

        public static native TemplateInstance confirm(User newUser);

        public static native TemplateInstance logoutFirst();

        public static native TemplateInstance welcome();
    }

    /**
     * Login page
     */
    public TemplateInstance login() {
        return Templates.login();
    }

    /**
     * Welcome page at the end of registration
     */
    @Authenticated
    public TemplateInstance welcome() {
        return Templates.welcome();
    }

    /**
     * Manual login form
     */
    @POST
    public Response manualLogin(@NotBlank @RestForm String userName,
                                @NotBlank @RestForm String password) {
        if (validationFailed()) {
            login();
        }
        User user = User.findRegisteredByUserName(userName);
        if (user == null
            || !BcryptUtil.matches(password, user.password)) {
            validation.addError("userName", "Invalid username/pasword");
            prepareForErrorRedirect();
            login();
        }
        NewCookie cookie = security.makeUserCookie(user);
        return Response.seeOther(Router.getURI(Application::index)).cookie(cookie).build();
    }


    /**
     * Manual registration form, sends confirmation email
     */
    @POST
    public TemplateInstance register(@RestForm @NotBlank @Email String email) {
        if (validationFailed())
            login();
        User newUser = User.findUnconfirmedByEmail(email);
        if (newUser == null) {
            newUser = new User();
            newUser.email = email;
            newUser.status = UserStatus.CONFIRMATION_REQUIRED;
            newUser.confirmationCode = UUID.randomUUID().toString();
            newUser.persist();
        }
        // send the confirmation code again
        io.truthencode.ddo.codex.email.Emails.confirm(newUser);
        return Templates.register(email);
    }


    /**
     * Confirmation form, once email is verified, to add user info
     */
    public TemplateInstance confirm(@RestQuery String confirmationCode) {
        checkLogoutFirst();
        User newUser = checkConfirmationCode(confirmationCode);
        return Templates.confirm(newUser);
    }

    private void checkLogoutFirst() {
        if (getUser() != null) {
            logoutFirst();
        }
    }

    /**
     * Link to logout page
     */
    public TemplateInstance logoutFirst() {
        return Templates.logoutFirst();
    }

    private User checkConfirmationCode(String confirmationCode) {
        // can't use error reporting as those are query parameters and not form fields
        if (StringUtils.isEmpty(confirmationCode)) {
            flash("message", "Missing confirmation code");
            flash("messageType", "error");
            redirect(Application.class).index();
        }
        User user = User.findForContirmation(confirmationCode);
        if (user == null) {
            flash("message", "Invalid confirmation code");
            flash("messageType", "error");
            redirect(Application.class).index();
        }
        return user;
    }

    @POST
    public Response complete(@RestQuery String confirmationCode,
                             @RestForm @NotBlank String userName,
                             @RestForm @Length(min = 8) String password,
                             @RestForm @Length(min = 8) String password2,
                             @RestForm @NotBlank String firstName,
                             @RestForm @NotBlank String lastName) {
        checkLogoutFirst();
        User user = checkConfirmationCode(confirmationCode);

        if (validationFailed())
            confirm(confirmationCode);

        validation.required("password", password);
        validation.required("password2", password2);
        validation.equals("password", password, password2);

        if (User.findRegisteredByUserName(userName) != null)
            validation.addError("userName", "User name already taken");
        if (validationFailed())
            confirm(confirmationCode);

        user.userName = userName;
        user.password = BcryptUtil.bcryptHash(password);
        user.firstName = firstName;
        user.lastName = lastName;
        user.confirmationCode = null;
        user.status = UserStatus.REGISTERED;

        ResponseBuilder responseBuilder = Response.seeOther(Router.getURI(Login::welcome));
        NewCookie cookie = security.makeUserCookie(user);
        responseBuilder.cookie(cookie);
        return responseBuilder.build();
    }
}
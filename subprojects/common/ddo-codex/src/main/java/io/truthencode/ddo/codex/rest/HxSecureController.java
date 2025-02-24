package io.truthencode.ddo.codex.rest;

import io.quarkiverse.renarde.htmx.HxController;
import io.quarkiverse.renarde.security.RenardeSecurity;
import io.quarkiverse.renarde.security.RenardeUser;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class HxSecureController<U extends RenardeUser> extends HxController {
    @Inject
    protected RenardeSecurity security;


    protected U getUser() {
        try {
            @SuppressWarnings("unchecked")
            var user = (U) this.security.getUser();
            return user;
        } catch (ClassCastException e) {
            throw new IllegalStateException("User is not of type RenardeUser", e);
        }

    }
}

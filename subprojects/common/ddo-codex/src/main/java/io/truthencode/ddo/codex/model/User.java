package io.truthencode.ddo.codex.model;



import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import io.truthencode.ddo.codex.model.security.SecurityRoles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import io.quarkiverse.renarde.security.RenardeUser;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "user_table")
public class User extends PanacheEntity implements RenardeUser {

    @Column(nullable = false)
    public String email;
    @Column(unique = true)
    public String userName;
    public String password;
    public String firstName;
    public String lastName;
    public boolean isAdmin;

    @Column(unique = true)
    public String confirmationCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public UserStatus status;

    //
    // Helpers

    public static User findUnconfirmedByEmail(String email) {
        return find("LOWER(email) = ?1 AND status = ?2", email.toLowerCase(), UserStatus.CONFIRMATION_REQUIRED).firstResult();
    }

    public static User findRegisteredByUserName(String username) {
        return find("LOWER(userName) = ?1 AND status = ?2", username.toLowerCase(), UserStatus.REGISTERED).firstResult();
    }

    public static User findByUserName(String username) {
        return find("LOWER(userName) = ?1", username.toLowerCase()).firstResult();
    }

    public static User findForContirmation(String confirmationCode) {
        return find("confirmationCode = ?1 AND status = ?2", confirmationCode, UserStatus.CONFIRMATION_REQUIRED).firstResult();
    }

    private String _user;

    @Override
    public Set<String> roles() {
        Set<String> roles = new HashSet<>();
        if(isAdmin) {
            roles.add(SecurityRoles.Admin.name());
        }
        return roles;
    }

    @Override
    public String userId() {
return userName;
    }

    @Override
    public boolean registered() {
        return status == UserStatus.REGISTERED;
    }
}
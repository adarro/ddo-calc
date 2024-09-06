package io.truthencode.ddo.codex.util;


import io.truthencode.ddo.codex.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import io.quarkiverse.renarde.security.RenardeSecurity;
import io.quarkiverse.renarde.security.RenardeUser;
import io.quarkiverse.renarde.security.RenardeUserProvider;


@ApplicationScoped
public class MySecuritySetup implements RenardeUserProvider {

    @Inject
    RenardeSecurity security;

    @Override
    public RenardeUser findUser(String tenantId, String id) {
        return User.findByUserName(id);
    }
}

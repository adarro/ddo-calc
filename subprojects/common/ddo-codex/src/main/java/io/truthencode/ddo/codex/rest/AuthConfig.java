package io.truthencode.ddo.codex.rest;

import io.quarkus.arc.Arc;
import io.quarkus.qute.TemplateExtension;
import io.quarkus.security.identity.CurrentIdentityAssociation;
import org.eclipse.microprofile.config.ConfigProvider;

import java.util.Set;

@TemplateExtension(namespace = "auth")
public class AuthConfig {
    private AuthConfig() {
        throw new UnsupportedOperationException("Private Utility Class Cannot Be Instantiated");
    }
    
    public static   boolean DEV_USER() {
        return ConfigProvider.getConfig().getValue("devonly.dev-user", Boolean.class);
    }

    public static boolean OIDC_GOOGLE() {
        return ConfigProvider.getConfig()
            .getOptionalValue("quarkus.oidc.google.provider", String.class).isPresent();
    }

    public static boolean OIDC_GITHUB() {
        return ConfigProvider.getConfig()
            .getOptionalValue("quarkus.oidc.github.provider", String.class).isPresent();
    }

    public static String name() {
        try (var inh = Arc.container().instance(CurrentIdentityAssociation.class)) {
            return inh.get().getIdentity().getPrincipal().getName();
        }
    }

    public static Set<String> roles() {
        try (var inh = Arc.container().instance(CurrentIdentityAssociation.class)) {
            return inh.get().getIdentity().getRoles();
        }
    }
}

package io.truthencode.ddo.codex.util;

import io.quarkus.runtime.LaunchMode;
import io.quarkus.runtime.StartupEvent;
import io.truthencode.ddo.codex.model.User;
import io.truthencode.ddo.codex.model.UserStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@ApplicationScoped
public class StartUp {
    private static final Logger log = LoggerFactory.getLogger(StartUp.class);
    @Inject
    DevOnlyConfig config;
    /**
     * This method is executed at the start of your application
     */
    @Transactional
    public void start(@Observes StartupEvent evt) throws IOException {
        if (LaunchMode.current().isDevOrTest() && config.devUser() && User.findByAuthId("manual", "dev") == null) {
            log.info("Creating dev user");
            User user = new User();
            user.email = "nobody@example.com";
            user.firstName = "dev";
            user.lastName = "dev";
            user.tenantId = "manual";
            user.authId = "dev";
            user.userName = "dev";
            user.isAdmin = true;
            user.status = UserStatus.REGISTERED;
            user.persist();

        }

    }
}
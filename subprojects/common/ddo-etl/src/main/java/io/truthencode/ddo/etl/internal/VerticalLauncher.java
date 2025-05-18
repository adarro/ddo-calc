package io.truthencode.ddo.etl.internal;

import io.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.enterprise.inject.Instance;
import org.jboss.logging.Logger;

/**
 * Conceptual class to launch all verticles
 */
@ApplicationScoped
public class VerticalLauncher {
private final Logger logger = Logger.getLogger(VerticalLauncher.class);

    /**
     * Launch all verticles
     * @param event Startup event data
     * @param vertx injected Vertx instance
     * @param verticles injected verticle instances to launch
     */
    public void launch(@Observes Startup event, Vertx vertx, Instance<AbstractVerticle> verticles) {
        
        for (AbstractVerticle verticle : verticles) {            
            logger.infof("VerticalLauncher.launch {}", verticle.getClass().getName());
            vertx.deployVerticle(verticle).await().indefinitely();
        }

    }
}

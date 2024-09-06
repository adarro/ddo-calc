package io.truthencode.ddo.codex.model;

import io.quarkiverse.renarde.Controller;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Internal class used to represent the entities in the codex.
 */
@Singleton
public class Entities {

    private static final Logger logger = Logger.getLogger(Entities.class);
    @Inject
    BeanManager beanManager;

    private final Map<String, Class<?>> entities = new HashMap<>();

    void onStart(@Observes StartupEvent ev) {
        final String SPACE = " ";
        final AtomicInteger counter = new AtomicInteger();
        final Set<Bean<?>> beans = beanManager.getBeans(Object.class, new AnnotationLiteral<Any>() {
        });
        beans.stream()
            .filter(bean ->
                bean.getBeanClass().isAssignableFrom(Controller.class))
            .forEach(bean -> {
                logger.info(counter.getAndIncrement() + SPACE + bean.getBeanClass().getName());
                entities.put(bean.getBeanClass().getSimpleName(), bean.getBeanClass());
            });
        logger.info("Found " + counter.get() + " controllers");
    }


}

package io.truthencode.ddo.dal.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.truthencode.ddo.dal.entity.Effect;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repository for managing Effect entities in the data access layer.
 * Provides reactive CRUD operations for Effect entities using Panache repository pattern.
 */
@ApplicationScoped
public class EffectRepository implements PanacheRepository<Effect> {
    /**
     * Default constructor required by JPA specification.
     * Ensures compatibility with Java Persistence API initialization requirements.
     */
    public EffectRepository() {
        // JPA Required Default constructor
    }
}

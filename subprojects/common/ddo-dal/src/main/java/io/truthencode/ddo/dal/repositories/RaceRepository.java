package io.truthencode.ddo.dal.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.truthencode.ddo.dal.entity.Race;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repository for managing Race entities in the database.
 * Provides reactive database operations for Race objects using Panache repository pattern.
 */
@ApplicationScoped
public class RaceRepository implements PanacheRepository<Race> {
    /**
         * Default constructor required by JPA specification.
         * Ensures compatibility with Java Persistence API initialization mechanisms.
         */
    public RaceRepository() {
        // JPA Required Default constructor
    }
}

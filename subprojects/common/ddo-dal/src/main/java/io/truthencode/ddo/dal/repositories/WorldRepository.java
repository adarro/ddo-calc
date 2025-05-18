package io.truthencode.ddo.dal.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.truthencode.ddo.dal.entity.World;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repository for managing World entities in the database.
 * Implements Panache Repository pattern for reactive database operations.
 * Scoped at the application level to provide a singleton instance for World entity persistence.
 */
@ApplicationScoped
@SuppressWarnings("default-constructor")
public class WorldRepository implements PanacheRepository<World> {
    /**
     * Default constructor for the WorldRepository.
     */
    public WorldRepository() {
        // JPA Required Default constructor
    }
}

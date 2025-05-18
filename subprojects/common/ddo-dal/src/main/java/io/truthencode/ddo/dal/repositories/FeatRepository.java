package io.truthencode.ddo.dal.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.truthencode.ddo.dal.entity.Feat;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repository for managing Feat entities using Quarkus Hibernate Reactive Panache.
 * <p>
 * This repository provides data access layer operations for Feat entities,
 * leveraging the reactive capabilities of Panache Repository.
 */
@ApplicationScoped
public class FeatRepository implements PanacheRepository<Feat> {
    /**
     * Default constructor for FeatRepository.
     * <p>
     * This constructor is required by JPA (Java Persistence API)
     * to create instances of the repository without arguments.
     */
    public FeatRepository() {
        // JPA Required Default constructor
    }
}

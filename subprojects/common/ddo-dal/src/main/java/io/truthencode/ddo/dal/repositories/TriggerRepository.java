package io.truthencode.ddo.dal.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.truthencode.ddo.dal.entity.Triggers;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repository for managing Triggers entities using Quarkus Hibernate Reactive Panache.
 * This repository provides standard CRUD operations for Triggers database interactions.
 *
 * @see PanacheRepository
 * @see Triggers
 */
@ApplicationScoped
public class TriggerRepository implements PanacheRepository<Triggers> {
    /**
         * Default constructor for TriggerRepository.
         * Required by JPA for creating instances of the repository.
         */
    public TriggerRepository() {
        // JPA Required Default constructor
    }
}

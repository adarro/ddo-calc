package io.truthencode.ddo.dal.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.truthencode.ddo.dal.entity.RaceFamily;
import jakarta.enterprise.context.ApplicationScoped;


/**
 * Repository for managing RaceFamily entities using Quarkus Hibernate Reactive Panache.
 * This repository provides data access layer operations for RaceFamily entities.
 */
@ApplicationScoped
public class RaceFamilyRepository implements PanacheRepository<RaceFamily> {
    /**
         * Default constructor for RaceFamilyRepository.
         * Required by JPA for creating instances of the repository.
         */
    public RaceFamilyRepository() {
        // JPA Required Default constructor
    }
}

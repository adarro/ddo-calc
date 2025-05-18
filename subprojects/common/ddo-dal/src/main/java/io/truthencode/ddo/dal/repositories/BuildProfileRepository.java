package io.truthencode.ddo.dal.repositories;


import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.truthencode.ddo.dal.entity.BuildProfile;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repository for managing BuildProfile entities using Quarkus Hibernate Reactive Panache.
 * This repository provides data access layer operations for BuildProfile entities.
 *
 * @see PanacheRepository
 * @see BuildProfile
 */
@ApplicationScoped
public class BuildProfileRepository implements PanacheRepository<BuildProfile> {
}

package io.truthencode.ddo.dal.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.truthencode.ddo.dal.entity.CharacterBuild;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repository for managing Character Build entities in a reactive Hibernate context.
 * This repository provides data access layer operations for CharacterBuild entities
 * using Quarkus Panache's reactive repository pattern.
 *
 * @see PanacheRepository
 * @see CharacterBuild
 */
@ApplicationScoped
public class CharacterBuildRepository implements PanacheRepository<CharacterBuild> {
}

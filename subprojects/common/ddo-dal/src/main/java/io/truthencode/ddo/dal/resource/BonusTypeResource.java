package io.truthencode.ddo.dal.resource;


import io.quarkus.hibernate.reactive.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import io.truthencode.ddo.dal.entity.BonusType;
import io.truthencode.ddo.dal.repositories.BonusTypeRepository;

/**
 * Represents a REST resource for managing BonusType entities using Panache repository pattern.
 * Provides HAL (Hypertext Application Language) support with a base path of "db/bonus-type".
 * Enables standard CRUD operations for BonusType entities with Long-based identifiers.
 */
@ResourceProperties(hal = true, path = "db/bonus-type")
public interface BonusTypeResource extends PanacheRepositoryResource<BonusTypeRepository, BonusType, Long> {
}


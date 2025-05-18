package io.truthencode.ddo.dal.resource;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import io.truthencode.ddo.dal.entity.Effect;
import io.truthencode.ddo.dal.repositories.EffectRepository;

/**
 * Represents a REST data resource for managing Effect entities using Panache repository pattern.
 * Provides HAL (Hypertext Application Language) support with a base path of "db/effect".
 * Extends PanacheRepositoryResource to enable standard CRUD operations for Effect entities.
 */
@ResourceProperties(hal = true, path = "db/effect")
public interface EffectResource  extends PanacheRepositoryResource<EffectRepository, Effect, Long> {
}

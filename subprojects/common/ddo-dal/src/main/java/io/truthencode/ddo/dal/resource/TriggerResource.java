package io.truthencode.ddo.dal.resource;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import io.truthencode.ddo.dal.entity.Triggers;
import io.truthencode.ddo.dal.repositories.TriggerRepository;

/**
 * Represents a REST resource for managing Trigger entities using Panache repository pattern.
 * Provides HAL (Hypertext Application Language) compliant REST endpoints for database triggers
 * with a base path of "db/trigger".
 */
@ResourceProperties(hal = true, path = "db/trigger")
public interface TriggerResource extends PanacheRepositoryResource<TriggerRepository, Triggers, Long> {
}

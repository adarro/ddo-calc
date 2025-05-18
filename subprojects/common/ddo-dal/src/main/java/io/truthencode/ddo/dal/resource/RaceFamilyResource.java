package io.truthencode.ddo.dal.resource;


import io.quarkus.hibernate.reactive.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import io.truthencode.ddo.dal.entity.RaceFamily;
import io.truthencode.ddo.dal.repositories.RaceFamilyRepository;

/**
 * Represents a REST resource for Race Family entities with HAL (Hypertext Application Language) support.
 * Provides CRUD operations for RaceFamily entities through a Panache-based repository.
 *
 * @see RaceFamilyRepository
 * @see RaceFamily
 */
@ResourceProperties(hal = true, path = "db/race-family")
public interface RaceFamilyResource  extends PanacheRepositoryResource<RaceFamilyRepository, RaceFamily, Long> {
}

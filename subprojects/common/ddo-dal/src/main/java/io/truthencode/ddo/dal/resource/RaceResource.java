package io.truthencode.ddo.dal.resource;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import io.truthencode.ddo.dal.entity.Race;
import io.truthencode.ddo.dal.repositories.RaceRepository;

/**
 * Represents a REST resource for Race entities, providing standard CRUD operations
 * through Panache repository resource interface.
 *
 * @see PanacheRepositoryResource
 * @see RaceRepository
 * @see Race
 */
@ResourceProperties(path = "db/race")
public interface  RaceResource extends PanacheRepositoryResource<RaceRepository, Race, Long> {
}

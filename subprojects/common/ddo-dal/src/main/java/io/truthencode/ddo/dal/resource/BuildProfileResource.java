package io.truthencode.ddo.dal.resource;


import io.quarkus.hibernate.reactive.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import io.truthencode.ddo.dal.entity.BuildProfile;
import io.truthencode.ddo.dal.repositories.BuildProfileRepository;

// url path should replace use with {user} closer to production

/**
 * REST resource interface for managing build profiles with HAL (Hypertext Application Language) support.
 * Provides CRUD operations for BuildProfile entities through a Panache repository.
 *
 * @see BuildProfile The entity type managed by this resource
 * @see BuildProfileRepository The repository used for data access
 */
@ResourceProperties(hal = true, path = "user/build-profile")
public interface BuildProfileResource extends PanacheRepositoryResource<BuildProfileRepository, BuildProfile, Long> {
}


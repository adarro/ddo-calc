package io.truthencode.ddo.dal.resource;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import io.truthencode.ddo.dal.entity.CharacterBuild;
import io.truthencode.ddo.dal.repositories.CharacterBuildRepository;
/**
 * Represents a REST resource for managing Character Build entities.
 *
 * This interface extends PanacheRepositoryResource to provide standard CRUD operations
 * for CharacterBuild entities with a Long-based identifier. The resource is configured
 * with HAL (Hypertext Application Language) support and is accessible via the "/profile/character" path.
 */
@ResourceProperties(hal = true, path = "profile/character")
public interface CharacterBuildResource extends PanacheRepositoryResource<CharacterBuildRepository, CharacterBuild, Long> {
}

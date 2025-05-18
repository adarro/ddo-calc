package io.truthencode.ddo.dal.resource;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import io.truthencode.ddo.dal.entity.Category;
import io.truthencode.ddo.dal.entity.Feat;
import io.truthencode.ddo.dal.repositories.CategoryRepository;
import io.truthencode.ddo.dal.repositories.FeatRepository;

/**
 * Resource interface for managing Feat entities through a Panache repository.
 * Provides RESTful data access layer operations with HAL (Hypertext Application Language) support.
 * Supports CRUD operations on Feat entities using the FeatRepository.
 */
@ResourceProperties(hal = true, path = "db/feat")
public interface FeatResource extends PanacheRepositoryResource<FeatRepository, Feat, Long> {
}

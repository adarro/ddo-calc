package io.truthencode.ddo.dal.resource;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import io.truthencode.ddo.dal.entity.Category;
import io.truthencode.ddo.dal.repositories.CategoryRepository;

/**
 * Represents a REST resource for Category entities with HAL (Hypertext Application Language) support.
 * Provides standard CRUD operations for Category entities through a Panache repository.
 *
 * @see PanacheRepositoryResource
 * @see CategoryRepository
 * @see Category
 */
@ResourceProperties(hal = true, path = "db/category")
public interface CategoryResource extends PanacheRepositoryResource<CategoryRepository, Category, Long> {
}

package io.truthencode.ddo.dal.repositories;


import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.truthencode.ddo.dal.entity.Category;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repository for managing Category entities using Quarkus Hibernate Reactive Panache.
 *
 * This repository provides standard CRUD operations for Category entities
 * within the application's data access layer.
 */
@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {
}

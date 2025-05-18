package io.truthencode.ddo.dal.repositories;


import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.truthencode.ddo.dal.entity.BonusType;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repository for managing BonusType entities using Quarkus Hibernate Reactive Panache.
 *
 * This repository provides standard CRUD operations for BonusType entities
 * within an application-scoped context.
 */
@ApplicationScoped
public class BonusTypeRepository implements PanacheRepository<BonusType> {
}

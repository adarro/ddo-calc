package io.truthencode.ddo.dal.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import io.truthencode.ddo.dal.entity.Category
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CategoryRepository : PanacheRepository<Category> {
    fun findByName(name: String) = find("name", name).firstResult()

    fun all() = listAll()
}
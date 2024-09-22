package io.truthencode.ddo.dal.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import io.truthencode.ddo.dal.entity.Triggers
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class TriggerRepository : PanacheRepository<Triggers> {
    fun findByName(name: String) = find("name", name).firstResult()
}
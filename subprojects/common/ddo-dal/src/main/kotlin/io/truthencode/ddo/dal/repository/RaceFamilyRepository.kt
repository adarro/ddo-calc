package io.truthencode.ddo.dal.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import io.truthencode.ddo.dal.entity.RaceFamily
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class RaceFamilyRepository : PanacheRepository<RaceFamily> {
    fun getByName(name: String) = find("name", name).firstResult()
}
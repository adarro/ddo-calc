package io.truthencode.ddo.dal.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import io.truthencode.ddo.dal.entity.Race
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class RaceRepository : PanacheRepository<Race>
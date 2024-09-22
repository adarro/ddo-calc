package io.truthencode.ddo.dal.entity.audit

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalDateTime

@Embeddable
open class Audit {
    @Column(name = "created_on")
    lateinit var createdOn: LocalDateTime

    @Column(name = "created_by")
    lateinit var createdBy: String

    @Column(name = "updated_on")
    lateinit var updatedOn: LocalDateTime

    @Column(name = "updated_by")
    lateinit var updatedBy: String
}
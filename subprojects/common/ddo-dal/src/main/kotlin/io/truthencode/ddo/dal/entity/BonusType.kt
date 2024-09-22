package io.truthencode.ddo.dal.entity

import io.truthencode.ddo.dal.entity.audit.Audit
import io.truthencode.ddo.dal.entity.audit.AuditListener
import io.truthencode.ddo.dal.entity.audit.Auditable
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
@EntityListeners(AuditListener::class)
class BonusType : Auditable {
    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(unique = true)
    lateinit var name: String

    @Embedded
    private var audit: Audit? = null

    override fun getAudit(): Audit? = audit

    override fun setAudit(audit: Audit) {
        this.audit = audit
    }
}
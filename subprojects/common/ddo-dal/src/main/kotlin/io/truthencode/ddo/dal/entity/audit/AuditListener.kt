package io.truthencode.ddo.dal.entity.audit

import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import java.time.LocalDateTime

class AuditListener {
    @PrePersist
    fun setCreatedOn(auditable: Auditable) {
        var audit: Audit? = auditable.getAudit()

        if (audit == null) {
            audit = Audit()
            auditable.setAudit(audit)
        }

        audit.createdOn = LocalDateTime.now()
        audit.createdBy = LoggedUser.get()
    }

    @PreUpdate
    fun setUpdatedOn(auditable: Auditable) {
        val audit: Audit? = auditable.getAudit()

        audit?.updatedOn = LocalDateTime.now()
        audit?.updatedBy = LoggedUser.get()
    }
}
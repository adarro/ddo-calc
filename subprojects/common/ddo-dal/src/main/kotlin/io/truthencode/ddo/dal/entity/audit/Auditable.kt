package io.truthencode.ddo.dal.entity.audit

interface Auditable {
    fun getAudit(): Audit?

    fun setAudit(audit: Audit)
}
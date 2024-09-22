package io.truthencode.ddo.dal.entity.audit

interface Auditable {
    abstract fun getAudit(): Audit?

    abstract fun setAudit(audit: Audit)
}
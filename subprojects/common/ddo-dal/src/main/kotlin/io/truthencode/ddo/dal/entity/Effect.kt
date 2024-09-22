package io.truthencode.ddo.dal.entity

import io.truthencode.ddo.dal.entity.audit.Audit
import io.truthencode.ddo.dal.entity.audit.AuditListener
import io.truthencode.ddo.dal.entity.audit.Auditable
import jakarta.persistence.ElementCollection
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
@EntityListeners(AuditListener::class)
class Effect : Auditable {
    @Id
    @GeneratedValue
    var id: Long? = null

    lateinit var key: String

    lateinit var name: String

    lateinit var description: String

    @OneToMany(targetEntity = Triggers::class)
    lateinit var triggersOn: List<Triggers>

    @OneToMany(targetEntity = Triggers::class)
    lateinit var triggersOf: List<Triggers>

    lateinit var generalDescription: String

    @OneToMany(targetEntity = Category::class)
    lateinit var categories: List<Category>

    @ManyToOne
    lateinit var bonusType: BonusType
    private var value: Int = 0

    @ElementCollection
    lateinit var scaling: List<Scaling>

    @Embedded
    private var audit: Audit? = null

    override fun getAudit(): Audit? = audit

    override fun setAudit(audit: Audit) {
        this.audit = audit
    }
}
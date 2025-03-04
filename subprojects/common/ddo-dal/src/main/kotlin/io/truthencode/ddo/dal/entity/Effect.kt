/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Effect.kt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

/** Represents an effect in the game system. */
@Entity
@EntityListeners(AuditListener::class)
class Effect : Auditable {
    /** The unique identifier for the effect. */
    @Id @GeneratedValue
    var id: Long? = null

    /** The value of the effect. */
    private var value: Int = 0

    /** The audit information for the effect. */
    @Embedded private var audit: Audit? = null

    /** The key of the effect. */
    lateinit var key: String

    /** The name of the effect. */
    lateinit var name: String

    /** The description of the effect. */
    lateinit var description: String

    /** The list of triggers that activate this effect. */
    @OneToMany(targetEntity = Triggers::class)
    lateinit var triggersOn: List<Triggers>

    /** The list of triggers that this effect activates. */
    @OneToMany(targetEntity = Triggers::class)
    lateinit var triggersOf: List<Triggers>

    /** A general description of the effect. */
    lateinit var generalDescription: String

    /** The categories this effect belongs to. */
    @OneToMany(targetEntity = Category::class)
    lateinit var categories: List<Category>

    /** The type of bonus this effect provides. */
    @ManyToOne lateinit var bonusType: BonusType

    /** The scaling factors for the effect. */
    @ElementCollection lateinit var scaling: List<Scaling>

    /**
     * Gets the audit information.
     *
     * @return the audit information.
     */
    override fun getAudit(): Audit? = audit

    /**
     * Sets the audit information.
     *
     * @param audit the audit information to set.
     */
    override fun setAudit(audit: Audit) {
        this.audit = audit
    }
}

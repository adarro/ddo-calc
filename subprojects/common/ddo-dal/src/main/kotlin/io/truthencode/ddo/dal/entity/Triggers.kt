/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Triggers.kt
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
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

/** Represents a trigger in the game system. */
@Entity
@Table(name = "trigger_table")
@EntityListeners(AuditListener::class)
open class Triggers : Auditable {
    /** The unique identifier for the trigger. */
    @Id @GeneratedValue
    var id: Long? = null

    /** The audit information for the trigger. */
    @Embedded private var audit: Audit? = null

    /** The name of the trigger. */
    @Column(unique = true)
    lateinit var name: String

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

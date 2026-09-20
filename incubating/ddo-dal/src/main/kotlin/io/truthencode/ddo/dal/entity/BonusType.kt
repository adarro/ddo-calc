/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: BonusType.kt
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

/**
 * BonusType represents a type of bonus in the game system. This entity is used to track and manage
 * different bonus categories.
 *
 * Bonus types are important for determining bonus stacking rules, as certain types of bonuses do
 * not stack with others of the same type. For example, multiple enhancement bonuses to armor class
 * will not stack, only the highest value will apply.
 *
 * Properties:
 * - id: Unique identifier for the bonus type
 * - name: Unique name of the bonus type
 * - audit: Tracks creation and modification metadata
 */
@Entity
@EntityListeners(AuditListener::class)
class BonusType : Auditable {
    /** The ID of the bonus type */
    @Id @GeneratedValue
    var id: Long? = null

    @Embedded private var audit: Audit? = null

    /** The name of the bonus type */
    @Column(unique = true)
    lateinit var name: String

    override fun getAudit(): Audit? = audit

    override fun setAudit(audit: Audit) {
        this.audit = audit
    }
}

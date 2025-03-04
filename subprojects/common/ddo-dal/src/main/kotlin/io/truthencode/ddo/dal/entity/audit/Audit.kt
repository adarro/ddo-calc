/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Audit.kt
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
package io.truthencode.ddo.dal.entity.audit

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalDateTime

/** Audit information for an entity. */
@Suppress("PACKAGE_OR_CLASSIFIER_REDECLARATION")
@Embeddable
open class Audit {
    /** The date and time the entity was created */
    @Column(name = "created_on")
    lateinit var createdOn: LocalDateTime

    /** The user who created the entity */
    @Column(name = "created_by")
    lateinit var createdBy: String

    /** The date and time the entity was last updated */
    @Column(name = "updated_on")
    lateinit var updatedOn: LocalDateTime

    /** The user who last updated the entity */
    @Column(name = "updated_by")
    lateinit var updatedBy: String
}

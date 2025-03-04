/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AuditListener.kt
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

import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import java.time.LocalDateTime

/** Listener for audit information. */
@Suppress("PACKAGE_OR_CLASSIFIER_REDECLARATION")
class AuditListener {
    /**
     * Sets the created on date and time on record creation.
     *
     * @param auditable
     */
    @PrePersist
    fun setCreatedOn(auditable: Auditable) {
        var audit: Audit? = auditable.getAudit()

        audit
            ?: run {
                audit = Audit()
                auditable.setAudit(audit)
            }

        audit?.createdOn = LocalDateTime.now()
        audit?.createdBy = LoggedUser.get()
    }

    /**
     * Sets the updated on date and time on record update.
     *
     * @param auditable
     */
    @PreUpdate
    fun setUpdatedOn(auditable: Auditable) {
        val audit: Audit? = auditable.getAudit()

        audit?.updatedOn = LocalDateTime.now()
        audit?.updatedBy = LoggedUser.get()
    }
}

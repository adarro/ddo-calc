/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Auditable.kt
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

/** Interface for entities that have audit information. */
@Suppress("PACKAGE_OR_CLASSIFIER_REDECLARATION")
interface Auditable {
    /**
     * Gets the audit information.
     *
     * @return the audit information.
     */
    fun getAudit(): Audit?

    /**
     * Sets the audit information.
     *
     * @param audit
     */
    fun setAudit(audit: Audit)
}

/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: AuditListener.java
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
package io.truthencode.ddo.dal.entity.audit;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Instant;

/** Listener for audit information. */
public class AuditListener {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Sets the created on date and time on record creation.
     *
     * @param auditable the entity being audited
     */
    @PrePersist
    public void setCreatedOn(Auditable auditable) {
        log.warn("audit-- Setting created on date and time");
        Audit audit = auditable.findAudit();

        if (audit == null) {
            audit = new Audit();
            auditable.loadAudit(audit);
        }

        audit.setCreatedOn(Instant.now());
        audit.setCreatedBy(LoggedUser.get());
    }

    /**
     * Sets the updated on date and time on record update.
     *
     * @param auditable the entity being audited
     */
    @PreUpdate
    public void setUpdatedOn(Auditable auditable) {
        log.warn("audit-- Setting updated on date and time");
        Audit audit = auditable.findAudit();

        if (audit != null) {
            audit.setUpdatedOn(Instant.now());
            audit.setUpdatedBy(LoggedUser.get());
        }
    }
}
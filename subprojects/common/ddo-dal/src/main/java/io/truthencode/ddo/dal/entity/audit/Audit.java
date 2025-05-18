/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Audit.java
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

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;

/** Audit information for an entity.
 * These methods should not generally be called directly.
 * Persistence should be handled by the AuditListener.
 */
@Embeddable
public class Audit {
    /** The date and time the entity was created */
    @Column(name = "created_on", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createdOn;

    /** The user who created the entity */
    @Column(name = "created_by")
    private String createdBy;

    /** The date and time the entity was last updated */
    @UpdateTimestamp
    @Column(name = "updated_on", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant updatedOn;

    /** The user who last updated the entity */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * Gets the date and time the entity was created.
     * @return the date and time the entity was created.
     */
    public Instant getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the date and time the entity was created.
     * @param createdOn the date and time the entity was created.
     */
    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Gets the user who created the entity.
     * @return the user who created the entity.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the entity.
     * @param createdBy the user who created the entity.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the date and time the entity was last updated.
     * @return  the date and time the entity was last updated.
     */
    public Instant getUpdatedOn() {
        return updatedOn;
    }

    /**
     * Sets the date and time the entity was last updated.
     * @param updatedOn the date and time the entity was last updated.
     */
    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    /**
     * Gets the user who last updated the entity.
     * @return the user who last updated the entity.
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Sets the user who last updated the entity.
     * @param updatedBy the user who last updated the entity.
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}

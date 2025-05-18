/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Triggers.java
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
package io.truthencode.ddo.dal.entity;

import io.truthencode.ddo.dal.entity.audit.Audit;
import io.truthencode.ddo.dal.entity.audit.AuditListener;
import io.truthencode.ddo.dal.entity.audit.Auditable;
import jakarta.persistence.*;

/**
 * /**
 * Checks if the trigger is currently active.
 *
 * @return true if the trigger is active, false otherwise
 */

/**
 * Represents a game trigger entity with configurable active and passive states.
 * <p>
 * This class defines a database-mapped trigger with a unique identifier, name,
 * and audit capabilities. Triggers can be marked as active or passive and support
 * tracking of creation, modification, and other audit-related metadata.
 *
 * @see Auditable
 * @see Audit
 */
@Entity
@Table(name = "trigger_table")
@EntityListeners(AuditListener.class)
public class Triggers implements Auditable {
    /**
         * Default constructor for Triggers entity.
         * <p>
         * Required by JPA for creating instances of the Triggers class.
         * This no-argument constructor allows JPA providers to instantiate
         * the entity during persistence operations.
         */
    public Triggers() {
        // JPA Required Default constructor
    }

    /**
     * The unique identifier for the trigger.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The audit information for the trigger.
     */
    @Embedded
    private Audit audit;

    /**
     * The name of the trigger.
     */
    @Column(unique = true)
    private String name;

    private boolean active = false;

    private boolean passive = false;

    /**
     * Gets the unique identifier for the trigger.
     *
     * @return the trigger's unique identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the trigger.
     *
     * @param id the unique identifier to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the trigger.
     *
     * @return the trigger's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the trigger.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the trigger is currently active.
     *
     * @return true if the trigger is active, false otherwise
     */
    public boolean isActive() {
        return active;
    }


    /**
     * Sets the active state of the trigger.
     *
     * @param active the active state to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Checks if the trigger is passive.
     *
     * @return true if the trigger is passive, false otherwise
     */
    public boolean isPassive() {
        return passive;
    }

    /**
     * Sets the passive state of the trigger.
     *
     * @param passive the passive state to set
     */
    public void setPassive(boolean passive) {
        this.passive = passive;
    }

    /**
     * Gets the audit information.
     *
     * @return the audit information.
     */
    @Override
    public Audit findAudit() {
        return audit;
    }

    /**
     * Sets the audit information.
     *
     * @param audit the audit information to set.
     */
    @Override
    public void loadAudit(Audit audit) {
        this.audit = audit;
    }
}

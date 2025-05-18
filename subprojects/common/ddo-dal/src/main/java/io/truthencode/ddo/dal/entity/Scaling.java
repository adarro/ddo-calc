/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Scaling.java
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

/** Represents a scaling factor in the game system. */
@Entity
@EntityListeners(AuditListener.class)
public class Scaling implements Auditable {
    /** The unique identifier for the scaling factor. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The audit information for the scaling factor. */
    @Embedded
    private Audit audit;

    /** The name of the scaling factor. */
    @Column(unique = true)
    private String name;

/**
         * Default constructor for creating a Scaling entity.
         * Required by JPA for object instantiation.
         */
        public Scaling() {
        // Default constructor required by JPA
    }

    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the scaling factor.
     * @param id the unique identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the scaling factor.
     * @return the name of the scaling factor.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the scaling factor.
     * @param name the name of the scaling factor to set.
     */
    public void setName(String name) {
        this.name = name;
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

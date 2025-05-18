/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: BonusType.java
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
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
@EntityListeners(AuditListener.class)
@Table(name = "bonus_type")
public class BonusType implements Auditable {
    /** The ID of the bonus type */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Audit audit;

    /** The name of the bonus type */
    @Column(unique = true)
    private String name;

    /**
     * Gets the ID of the bonus type.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the bonus type.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the bonus type.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the bonus type.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Audit findAudit() {
        return audit;
    }

    @Override
    public void loadAudit(Audit audit) {
        this.audit = audit;
    }
}
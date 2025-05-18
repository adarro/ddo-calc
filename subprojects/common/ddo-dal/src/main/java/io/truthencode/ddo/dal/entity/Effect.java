/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: Effect.java
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

import java.util.List;

/**
 * Represents an effect in the game system.
 */
@Entity
@EntityListeners(AuditListener.class)
public class Effect implements Auditable {
    /**
     * The unique identifier for the effect.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The value of the effect.
     */
    private int value = 0;

    /**
     * The audit information for the effect.
     */
    @Embedded
    private Audit audit = new Audit();

    /**
     * The key of the effect.
     */
    private String key;

    /**
     * The name of the effect.
     */
    private String name;

    /**
     * The description of the effect.
     */
    private String description;

    /**
     * The list of triggers that activate this effect.
     */
    @OneToMany(targetEntity = Triggers.class)
    private List<Triggers> triggersOn;

    /**
     * The list of triggers that this effect activates.
     */
    @OneToMany(targetEntity = Triggers.class)
    private List<Triggers> triggersOf;

    /**
     * A general description of the effect.
     */
    private String generalDescription;

    /**
     * The categories this effect belongs to.
     */
    @OneToMany(targetEntity = Category.class)
    private List<Category> categories;

    /**
     * The type of bonus this effect provides.
     */
    @ManyToOne
    private BonusType bonusType;

    /**
     * The scaling factors for the effect.
     */
    @ElementCollection(targetClass = Scaling.class)
    private List<Scaling> scaling;

    /**
     * Gets the unique identifier for the effect.
     *
     * @return The ID of the effect.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the effect.
     *
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the numeric value of the effect.
     *
     * @return The value of the effect.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the numeric value of the effect.
     *
     * @param value The value to set.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets the key of the effect.
     *
     * @return The key of the effect.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key of the effect.
     *
     * @param key The key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the name of the effect.
     *
     * @return The name of the effect.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the effect.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the effect.
     *
     * @return The description of the effect.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the effect.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the list of triggers that activate this effect.
     *
     * @return The list of triggers that activate this effect.
     */
    public List<Triggers> getTriggersOn() {
        return triggersOn;
    }

    /**
     * Sets the list of triggers that activate this effect.
     *
     * @param triggersOn The list of triggers to set.
     */
    public void setTriggersOn(List<Triggers> triggersOn) {
        this.triggersOn = triggersOn;
    }

    /**
     * Gets the list of triggers that this effect activates.
     *
     * @return The list of triggers that this effect activates.
     */
    public List<Triggers> getTriggersOf() {
        return triggersOf;
    }

    /**
     * Sets the list of triggers that this effect activates.
     *
     * @param triggersOf The list of triggers to set.
     */
    public void setTriggersOf(List<Triggers> triggersOf) {
        this.triggersOf = triggersOf;
    }

    /**
     * Gets the general description of the effect.
     *
     * @return The general description of the effect.
     */
    public String getGeneralDescription() {
        return generalDescription;
    }

    /**
     * Sets the general description of the effect.
     *
     * @param generalDescription The general description to set.
     */
    public void setGeneralDescription(String generalDescription) {
        this.generalDescription = generalDescription;
    }

    /**
     * Gets the categories this effect belongs to.
     *
     * @return The list of categories this effect belongs to.
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * Sets the categories this effect belongs to.
     *
     * @param categories The list of categories to set.
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * Gets the type of bonus this effect provides.
     * @return The bonus type of this effect.
     */
    public BonusType getBonusType() {
        return bonusType;
    }

    /**
     * Sets the type of bonus this effect provides.
     * @param bonusType The bonus type to set.
     */
    public void setBonusType(BonusType bonusType) {
        this.bonusType = bonusType;
    }


    /**
     * Gets the scaling factors for the effect.
     *
     * @return The list of scaling factors for the effect.
     */
    public List<Scaling> getScaling() {
        return scaling;
    }

    /**
     * Sets the scaling factors for the effect.
     *
     * @param scaling The list of scaling factors to set.
     */
    public void setScaling(List<Scaling> scaling) {
        this.scaling = scaling;
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

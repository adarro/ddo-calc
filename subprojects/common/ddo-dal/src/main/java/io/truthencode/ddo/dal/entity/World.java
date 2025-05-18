/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: World.java
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

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Individual in-game servers are referred to as worlds.
 */
@Entity
public class World {
    /**
         * Default constructor required by JPA specification.
         * Provides a no-argument constructor for object instantiation by JPA providers.
         */
    public World() {
        // JPA Required Default constructor
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the world
     */
    private String name;

    // Getters and setters

    /**
     * Gets the unique identifier for the world.
     * @return  the unique identifier for the world.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the world.
     * @param id  the unique identifier for the world.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the world.
     * @return the name of the world.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the world.
     * @param name the name of the world.
     */
    public void setName(String name) {
        this.name = name;
    }
}

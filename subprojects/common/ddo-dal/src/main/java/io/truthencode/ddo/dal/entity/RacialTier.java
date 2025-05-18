/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: RacialTier.java
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

/**
 * Represents the tier or access level of a racial option in the game.
 * Defines the different categories of racial selections available to players.
 */
public enum RacialTier {
    /**
     * Represents a free racial tier or option that is available to all players without additional purchase.
     */
    FREE,
    /**
     * Represents a racial tier or option that requires additional purchase via favor or the DDO Store and is not freely available to all players.
     */
    PREMIUM,
    /**
     * Represents a racial tier or option that is available to all players without additional purchase.
     * Some iconic classes may be unlocked through favor.
     */
    ICONIC
}

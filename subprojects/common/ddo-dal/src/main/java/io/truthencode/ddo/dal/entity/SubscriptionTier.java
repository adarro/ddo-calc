/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: SubscriptionTier.java
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
 * Represents different subscription tiers for a service or product.
 * <p>
 * Defines the available levels of subscription access, ranging from free to premium and VIP.
 * Each tier provides varying degrees of features and benefits.
 */
public enum SubscriptionTier {
    /**
     * Represents a free tier with no additional cost.
     */
    FREE,
    /**
     * Represents a premium tier that includes the Free tier and some individually purchased features or services.
     * Also known as "Freemium" in some contexts.
     */
    PREMIUM,
    /**
     * A monthly subscription tier that provides additional benefits or features such as access to premium content or features.
     */
    VIP
}

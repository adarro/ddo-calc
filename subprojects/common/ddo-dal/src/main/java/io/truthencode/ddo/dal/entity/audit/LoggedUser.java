/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: LoggedUser.java
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

/** Represents the user that is currently logged in. */
public class LoggedUser {
    // Private constructor to prevent instantiation
    private LoggedUser() {
    }

    /**
     * Gets the currently logged-in user.
     *
     * @return the currently logged-in user.
     */
    public static String get() {
        throw new UnsupportedOperationException("Not yet implemented, need to implement OIDC and Forms Auth");
    }
}
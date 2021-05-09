/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
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
package io.truthencode.ddo.support;

/**
 * Created by adarr on 3/21/2017.
 */
public class AssertionStatus {
    /**
     * Convenience method used to determine assertion status.
     * Used internally to conditionally ignore assertion based unit tests.
     * @return true if assertions are enabled.
     */@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public static boolean isEnabled() { //    @org.jetbrains.annotations.Contract(pure = true)
        boolean assertOn = false;
        // *assigns* true if assertions are on.
        assert assertOn = true; // Intentional side effect!!!
        return assertOn;
    }
}

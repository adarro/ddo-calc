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
// group = "io.truthencode"
// version = "0.1-SNAPSHOT"

plugins {
    `java-platform`
}

description = "Project BOM.  Contains common dependencies for projects."

javaPlatform {
    allowDependencies()
}

dependencies {
    constraints {
        api(libs.logback.classic)
        api(libs.jetbrains.annotations)
        // JUnit
        api(libs.junit.junit5.bom)
    }

    // api(platform(":ddo-platform-scala"))
    // api(platform(":ddo-platform-kotlin"))
}
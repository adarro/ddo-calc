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
group = "io.truthencode"
version = "0.1-SNAPSHOT"

plugins {
    `java-platform`
}

description = "Project BOM.  Contains common dependencies for Kotlin projects."

dependencies {
    constraints {
        api(libs.kotlin.bom)
        api(libs.kotlin.reflect)
        api(libs.kotlin.stdlib)
        api(libs.kotlin.stdlib.jdk8)
        api(libs.kotlin.stdlib.jdk7)
        api(libs.kotlin.stdlib.common)
        // TODO: Add moshi-kotlin / reflect et all to kotlin-platform
        api(libs.bundles.kotest)
        api(libs.squareup.moshi.kotlin)
        api(libs.squareup.moshi)
        api(libs.squareup.moshi.adapters)
        api(libs.squareup.moshi.kotlin.codegen)
    }
}
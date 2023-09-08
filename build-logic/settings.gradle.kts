/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2022-2023 Andre White.
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

pluginManagement {
    repositories {
        mavenCentral()
        // used for local development and while building by travis ci and jitpack.io
        mavenLocal()
        // used to download antlr-kotlin gradle plugin
        gradlePluginPortal()
        // used to download antlr-kotlin-gradle-plugin
        maven("https://jitpack.io")
    }

    val foojayResolverPluginVersionversion: String by settings
    val palantirPluginVersion: String by settings

    plugins {
//        id("org.kordamp.gradle.project") version kordampGradlePluginVersion
//        id("org.kordamp.gradle.jar") version kordampGradlePluginVersion
//        id("org.kordamp.gradle.minpom") version kordampGradlePluginVersion
//        id("com.mooltiverse.oss.nyx") version mooltiverseNyxPluginVersion
        id("org.gradle.toolchains.foojay-resolver-convention") version foojayResolverPluginVersionversion
        id("com.palantir.baseline") version palantirPluginVersion
        id("com.palantir.baseline-config") version palantirPluginVersion
        id("org.inferred.processors") version "3.7.0"
        id("org.scoverage") version "8.0.3"
//        id("ru.vyarus.mkdocs") version "3.0.0"
    }
}

plugins {
//    id("com.mooltiverse.oss.nyx")
    id("org.gradle.toolchains.foojay-resolver-convention")
}

dependencyResolutionManagement {
    versionCatalogs {
        // declares an additional catalog, named 'testLibs', from the 'test-libs.versions.toml' file
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}
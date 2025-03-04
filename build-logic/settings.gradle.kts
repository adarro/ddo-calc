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
// build-logic
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
    val quarkusPlatformVersion: String by settings
    val refreshVersionsPluginVersion: String by settings

    plugins {
//        id("org.kordamp.gradle.project") version kordampGradlePluginVersion
//        id("org.kordamp.gradle.jar") version kordampGradlePluginVersion
//        id("org.kordamp.gradle.minpom") version kordampGradlePluginVersion
//        id("com.mooltiverse.oss.nyx") version mooltiverseNyxPluginVersion
        id("org.gradle.toolchains.foojay-resolver-convention") version foojayResolverPluginVersionversion
        id("com.palantir.baseline") version palantirPluginVersion
        id("com.palantir.baseline-config") version palantirPluginVersion
        id("org.inferred.processors") version "3.7.0"
        id("org.scoverage") version "8.1"
        id("io.quarkus") version quarkusPlatformVersion
        id("de.fayard.refreshVersions") version refreshVersionsPluginVersion
//        id("ru.vyarus.mkdocs") version "3.0.0"
    }
}

plugins {
//    id("com.mooltiverse.oss.nyx")
    id("org.gradle.toolchains.foojay-resolver-convention")
    id("de.fayard.refreshVersions")
}

// refreshVersions {
//
//    this.featureFlags{enable(FeatureFlag.VERSIONS_CATALOG)
//
//    }
// }

/* Hackathon to use catalogs in convention plugin
Tracked in [github.com/gradle/gradle/issues/17863]
SO [stackoverflow.com/questions/69080927/gradle-7-2-gradle-kotlin-dsl-how-to-use-catalogs-in-convention-plugin]
Add custom code in included-builds build.gradle(.kts)
Add custom code in convention plugins settings.gradle(.kts)

``` kotlin
./build-src/build.gradle.kts
plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

println("from build-src build script: ${libs.versions.bb.get()}")

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

// ./build-src/settings.gradle.kts
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

// ./build-src/src/main/kotlin/foo.gradle.kts
import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()
println("from pre compiled script plugin: ${libs.versions.bb.get()}")

// ./build.gradle.kts
plugins {
    id("foo")
}

println("from main build script: ${libs.versions.bb.get()}")

// ./gradle/libs.versions.toml
[versions]
bb = "3.2.1"

// ./settings.gradle.kts
rootProject.name = "showcase"
includeBuild("build-src")

*/
dependencyResolutionManagement {
    versionCatalogs {
        // declares an additional catalog, named 'testLibs', from the 'test-libs.versions.toml' file
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"
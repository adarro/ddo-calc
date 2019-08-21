/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("org.kordamp.gradle:settings-gradle-plugin:0.25.0")
    }
}

apply(plugin = "org.kordamp.gradle.settings")


configure<org.kordamp.gradle.plugin.settings.ProjectsExtension> {
    val incubating = System.getenv("incubating")

    val dirList = mutableListOf("plugins", "docs", "subprojects")
    if (incubating != null )
        dirList += "incubating"
    logger.warn("DIRLIST ====> $dirList")
    directories = dirList
    enforceNamingConvention = false
}

rootProject.name = "ddo-calc-parent"
// include ':ddo-plot'
// include(":ddo-core")
// include ':ddo-etl'
// include ':ddo-data:ddo-data-mongo'
// include ':ddo-data'
// include ':ddo-route'

// requires updated R language gradle plugin.
// project(':ddo-plot').projectDir = "$rootDir/ddo-plot" as File
// project(":ddo-core").projectDir = File("$rootDir/ddo-core")
// project(':ddo-web').projectDir = "$rootDir/ddo-web" as File
//project(':ddo-etl').projectDir = "$rootDir/ddo-etl" as File
//project(':ddo-data:ddo-data-mongo').projectDir = "$rootDir/ddo-data/ddo-data-mongo" as File
//project(':ddo-data').projectDir = "$rootDir/ddo-data" as File
// project(':ddo-route').projectDir = "$rootDir/ddo-route" as File


// gradle.experimentalFeatures.enable()
//include 'ddo-odata'
//include 'ddo-odata-server'
val scoveragePluginVersion: String by settings
val versionsPluginVersion: String by settings
val buildScanPluginVersion: String by settings
val useLatestVersionsPluginVersion: String by settings
val testSetsPluginVersion: String by settings
val versionEyePluginVersion: String by settings
val taskTreePluginVersion: String by settings
val kordampGradlePluginVersion: String by settings

pluginManagement {
    plugins {
        id("org.unbroken-dome.test-sets") version testSetsPluginVersion
        id("org.scoverage") version scoveragePluginVersion
        //   id "findbugs"
        //  id "org.standardout.versioneye" version versionEyePluginVersion
        id("com.github.ben-manes.versions") version versionsPluginVersion
        id("se.patrikerdes.use-latest-versions") version useLatestVersionsPluginVersion
        id("com.gradle.build-scan") version buildScanPluginVersion
        id("com.dorongold.task-tree") version taskTreePluginVersion
        // kordamp opinionated gradle project plugins
        id("org.kordamp.gradle.project") version kordampGradlePluginVersion
        id("org.kordamp.gradle.bintray") version kordampGradlePluginVersion
        id("org.kordamp.gradle.build-scan") version kordampGradlePluginVersion
    }
}


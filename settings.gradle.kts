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

import de.fayard.refreshVersions.core.FeatureFlag
import de.fayard.refreshVersions.core.StabilityLevel
import java.nio.file.Files
import java.nio.file.Paths


rootProject.name = "ddo-calc-parent"

pluginManagement {
    val mooltiverseNyxPluginVersion: String by settings
    val foojayResolverPluginVersionversion: String by settings
    val refreshVersionsPluginVersion: String by settings

    plugins {
        id("com.mooltiverse.oss.nyx") version mooltiverseNyxPluginVersion
        id("org.gradle.toolchains.foojay-resolver-convention") version foojayResolverPluginVersionversion
        id("de.fayard.refreshVersions") version refreshVersionsPluginVersion
    }

    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("com.mooltiverse.oss.nyx")
    id("org.gradle.toolchains.foojay-resolver-convention")
    id("de.fayard.refreshVersions")
}

enableFeaturePreviewQuietly("TYPESAFE_PROJECT_ACCESSORS", "Type-safe project accessors")

/**
 * @see <a href="https://github.com/gradle/gradle/issues/19069">Feature request</a>
 */
fun Settings.enableFeaturePreviewQuietly(
    name: String,
    summary: String,
) {
    enableFeaturePreview(name)

    val logger: Any =
        org.gradle.util.internal.IncubationLogger::class.java
            .getDeclaredField("INCUBATING_FEATURE_HANDLER")
            .apply { isAccessible = true }
            .get(null)

    @Suppress("UNCHECKED_CAST")
    val features: MutableSet<String> =
        org.gradle.internal.featurelifecycle.LoggingIncubatingFeatureHandler::class.java
            .getDeclaredField("features")
            .apply { isAccessible = true }
            .get(logger) as MutableSet<String>

    features.add(summary)
}

// at some point in the future, see if we can safely make this property optional so there is no build warning if it is
// not specified or create a sensible default
val projectFolderDelimiter: String by settings

@Suppress("CUSTOM_GETTERS_SETTERS")
val projectFolders: List<String>
    get() = settings.extra["projectFolders"]?.toString()?.split(projectFolderDelimiter) ?: listOf()

logger.info("checking $projectFolders for sub-projects")

/**
 *  reads the first part of a string up to the "."
 *  should be an Extension, but can not inline compile as one in settings.gradle.kts
 *
 *  so we're doing a quick one off verses polluting a buildSrc, um... further
 *
 *  @param str the string to parse
 *  @return the first part of the string
 */
fun readFirstPart(str: String): String = str.split(".").first()

logger.info("checking ${projectFolders.size} project folders")
projectFolders.forEach { dirName ->
    val directory = Paths.get("$rootDir/$dirName")
    if (Files.notExists(directory)) {
        logger.warn("$directory does not exist,  creating")
        Files.createDirectory(directory)
    }

    Files
        .find(
            directory,
            Integer.MAX_VALUE,
            { _: java.nio.file.Path, attributes: java.nio.file.attribute.BasicFileAttributes ->
                attributes.isDirectory
            },
        ).use { dir ->
            dir.forEach { dr ->
                val customName = dr.toFile().name
                // ({{f,s -> true}})
                val files =
                    dr
                        .toFile()
                        .listFiles { _, str -> str.matches(Regex("($customName|build)\\.gradle(\\.kts)?")) }

                if (files?.isEmpty() != true) {
                    if (files!!.size != 1) {
                        logger.warn("Multiple build files located in project directory $dr")
                    }
                    val projectDir = rootDir.toPath().relativize(dr)
                    val first = files.first()
                    val buildFileName = first.name
                    // build file name may be arbitrary but usually follows either build or the directory name.
                    // since we're mostly controlling the build we'll assume the containing folders' name.
                    val projectName = first.parentFile.name
                    logger.info("Including Project:$projectName \tprojectDir: $projectDir \tBuildFile: $buildFileName")
                    include(projectName)
                    project(":$projectName").projectDir = projectDir.toFile()
                    project(":$projectName").buildFileName = buildFileName
                }
            }
        }
}

if (System.getenv("enableCompositeBuild") == "true") {
    logger.info("Adding included builds")
    file("examples").listFiles()?.filter { ft -> ft.isDirectory }?.forEach { moduleBuild: File ->
        includeBuild(moduleBuild)
    }
}

@Suppress("UnstableApiUsage")
refreshVersions {
    // https://github.com/jmfayard/refreshVersions
    rejectVersionIf {
        candidate.stabilityLevel.isLessStableThan(StabilityLevel.Stable)
    }
    featureFlags {
        this.enable(FeatureFlag.VERSIONS_CATALOG)
    }
}

includeBuild("build-logic")
// includeBuild("include/ddo-avro")
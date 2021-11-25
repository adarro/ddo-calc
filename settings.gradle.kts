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


import java.nio.file.Files
import java.nio.file.Paths


rootProject.name = "ddo-calc-parent"


pluginManagement {
    //  Scala
    // Coverage
    val scoveragePluginVersion: String by settings
    // Avro
    val avroHuggerPluginVersion: String by settings
    val openApiGeneratorPluginVersion: String by settings


    val kordampGradlePluginVersion: String by settings
    val semVerPluginVersion: String by settings


    plugins {
        id("com.github.hierynomus.license") version "0.16.1"
        id("com.zlad.gradle.avrohugger") version avroHuggerPluginVersion
        id("com.chudsaviet.gradle.avrohugger") version avroHuggerPluginVersion
        id("org.openapi.generator") version openApiGeneratorPluginVersion
        id("org.scoverage") version scoveragePluginVersion

        id("org.kordamp.gradle.project") version kordampGradlePluginVersion
        id("net.thauvin.erik.gradle.semver") version semVerPluginVersion
    }

    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
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
 *  so we're doing a quick one off verses polluting a buildSrc, um... further
 *
 *  @param str the string to parsee
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

    Files.find(
        directory,
        Integer.MAX_VALUE,
        { path: java.nio.file.Path, attributes: java.nio.file.attribute.BasicFileAttributes ->
            attributes.isDirectory
        }).use { dir ->
        dir.forEach { dr ->
            val customName = dr.toFile().name
            // ({{f,s -> true}})
            val files = dr.toFile()
                .listFiles { _, str -> str.matches(Regex("($customName|build)\\.gradle(\\.kts)?")) }

            if (files?.isNullOrEmpty() != true) {
                if (files.size != 1) {
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

logger.info("Adding included builds")

if (System.getenv("enableCompositeBuild") == "true") {
    file("examples").listFiles().filter { ft -> ft.isDirectory }.forEach { moduleBuild: File ->
        includeBuild(moduleBuild)
    }
}

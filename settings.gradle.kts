/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
rootProject.name = "ddo-calc-parent"

// at some point in the future, see if we can safely make this property optional so there is no build warning if it is not specified or create a sensible default
val projectFolderDelimiter: String by settings
val projectFolders: List<String>
    get() =
        settings.extra["projectFolders"]?.toString()?.split(projectFolderDelimiter) ?: listOf()


/**
 *  should be an Extension, but can not inline compile as one in settings.gradle.kts
 *  so we're doing a quick one off verses polluting a buildSrc.
 */
fun readFirstPart(s: String): String {
    return s.split(".").first()
}


logger.info("checking ${projectFolders.size} project folders")
projectFolders.forEach { dirName ->
    // TODO: We should likely check that this path exists or build can bomb
    val directory = java.nio.file.Paths.get("${rootDir}/$dirName")
    java.nio.file.Files.find(
        directory,
        Integer.MAX_VALUE,
        { p: java.nio.file.Path, attributes: java.nio.file.attribute.BasicFileAttributes ->
            attributes.isDirectory
        }).use { dir ->
        dir.forEach { d ->
            val customName = d.toFile().name
            val files = d.toFile()
                .listFiles { _, s -> s.matches(Regex("($customName|build)\\.gradle(\\.kts)?")) } //({{f,s -> true}})

            if (files?.isNullOrEmpty() != true) {
                if (files.size != 1)
                    logger.warn("Multiple build files located in project directory $d")
                //   val projectDir = d.relativize(rootProject.getProjectDirectory.toPath)
                //   val projectDir = d.relativize(rootDir.toPath())
                // val projectDir = d // java.nio.file.Paths.get(d.toPath())
                val projectDir = rootDir.toPath().relativize(d)
                val first = files.first()
                val buildFileName = first.name
                // build file name may be arbitrary but usually follows either build or the directory name.
                // since we're mostly controlling the build we'll assume the containing folders' name.
                val projectName = first.parentFile.name
                logger.info("Including Project $projectName \t projectDir: $projectDir \t BuildFile: $buildFileName")
                include(projectName)
                logger.lifecycle("Including Project $projectName in $projectDir with using build file $buildFileName")
                project(":${projectName}").projectDir = projectDir.toFile()
                project(":${projectName}").buildFileName = buildFileName
            }
        }
    }
}

pluginManagement {
    val scoveragePluginVersion: String by settings
    val versionsPluginVersion: String by settings
    val buildScanPluginVersion: String by settings
    val useLatestVersionsPluginVersion: String by settings
    val testSetsPluginVersion: String by settings
    val versionEyePluginVersion: String by settings
    val taskTreePluginVersion: String by settings
    val kordampGradlePluginVersion: String by settings
    val scalaTestPluginVersion: String by settings
    val scalaGradleCrossBuildPluginVersion: String by settings
    val semVerPluginVersion: String by settings
    val editorConfigPluginVersion: String by settings
    val ktlintConventionPluginVersion: String by settings
    // Avro
    val avroHuggerPluginVersion: String by settings
    val openApiGeneratorPluginVersion: String by settings


    plugins {
        id("org.scoverage") version scoveragePluginVersion
        id("org.unbroken-dome.test-sets") version testSetsPluginVersion
        id("com.github.maiflai.scalatest") version scalaTestPluginVersion // "0.25"
        //   id "findbugs"
        //  id "org.standardout.versioneye" version versionEyePluginVersion
        id("com.github.ben-manes.versions") version versionsPluginVersion
        id("se.patrikerdes.use-latest-versions") version useLatestVersionsPluginVersion
        //  id("com.gradle.build-scan") version buildScanPluginVersion
        id("com.dorongold.task-tree") version taskTreePluginVersion
        // kordamp opinionated gradle project plugins
        id("org.kordamp.gradle.project") version kordampGradlePluginVersion
//        id("org.kordamp.gradle.bintray") version kordampGradlePluginVersion
//        id("org.kordamp.gradle.build-scan") version kordampGradlePluginVersion
        //       id("org.kordamp.gradle.scaladoc") version kordampGradlePluginVersion
        id("io.wusa.semver-git-plugin") version semVerPluginVersion
        id("org.ec4j.editorconfig") version editorConfigPluginVersion
        id("org.gradle.kotlin-dsl.ktlint-convention") version ktlintConventionPluginVersion
        id("com.github.prokod.gradle-crossbuild") version scalaGradleCrossBuildPluginVersion
        id("com.zlad.gradle.avrohugger") version avroHuggerPluginVersion
        id("com.chudsaviet.gradle.avrohugger") version avroHuggerPluginVersion
        id("org.openapi.generator") version openApiGeneratorPluginVersion
    }

     repositories {
           gradlePluginPortal()
        jcenter()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
}

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

rootProject.name = "ddo-calc-parent"

fun includeProject(projectDirName: String, projectName: String) {
    logger.info("I am in include project function")
    val baseDir = File(settingsDir, projectDirName)
    val projectDir = File(baseDir, projectName)
    val buildFileName = "${projectName}.scala-platform.gradle.kts"

    assert(projectDir.isDirectory())
    assert(File(projectDir, buildFileName).isFile())

    include(projectName)
    logger.info("Including Project $projectName in $projectDir with using build file $buildFileName")
    project(":${projectName}").projectDir = projectDir
    project(":${projectName}").buildFileName = buildFileName
}

// at some point in the future, see if we can safely make this property optional so there is no build warning if it is not specified or create a sensible default
val projectFolderDelimiter: String by settings
val projectFolders: List<String>
    get() =
        settings.extra["projectFolders"]?.toString()?.split(projectFolderDelimiter) ?: listOf()

logger.info("checking ${projectFolders.size} project folders")
projectFolders.forEach { dirName ->
    val subdir = File(rootDir, dirName)
    logger.info("checking for projects in subDir: ${subdir.name}")
    subdir.walkTopDown().maxDepth(1).forEach { dir ->
        val fileNames = listOf(dir.name, "build")
        var found = false
        for (f in fileNames) {
            val buildFileName = File(dir, "$f.gradle.kts")
            logger.info("looking in ${dir.name} for buildFile $buildFileName")
            if (buildFileName.exists()) {
                logger.info("FOUND: $buildFileName")
                includeProject(dirName, dir.name)
                found = true
                break
            }
        }
        if (!found) logger.warn("No build file found in did not exist in ${dir.name}")
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
    val semVerPluginVersion: String by settings
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
        id("org.kordamp.gradle.scaladoc") version kordampGradlePluginVersion
        id("io.wusa.semver-git-plugin") version semVerPluginVersion
    }
}


import com.diffplug.gradle.spotless.SpotlessTask
import io.truthencode.buildlogic.RecurseValue
import io.truthencode.buildlogic.Recursion
import org.gradle.kotlin.dsl.withType

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

plugins {
    id("com.diffplug.spotless")
}

enum class ScriptLanguage { GradleBuild, KotlinScriptBuild }

fun buildLang(): ScriptLanguage =
    if (project.buildFile.name.endsWith("kts")) ScriptLanguage.KotlinScriptBuild else ScriptLanguage.GradleBuild

fun walkBack(
    fileName: String,
    recurse: Recursion,
    proj: Project = project.rootProject,
): File? {
    val f = proj.file(fileName)
    return if (f.exists()) {
        f
    } else {
        when (recurse.recurseValue) {
            RecurseValue.NONE -> null // throw IOException("can not locate file $fileName")
            RecurseValue.FINITE -> {
                walkBack("../$fileName", recurse - 1, proj.rootProject)
            }

            RecurseValue.INFINITE -> {
                walkBack("../$fileName", recurse, proj.rootProject)
            }
        }
    }
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    ratchetFrom("origin/master")
    if (buildLang() == ScriptLanguage.KotlinScriptBuild) {
        logger.debug("SPOTLESS: configuring kotlin script formatting to ${project.name}")
        kotlinGradle {
//            ktlint("0.50.0")
            diktat("1.2.5").configFile(rootProject.file("diktat-analysis.yml"))
            target("**/src/main/kotlin/*.build.kts")
        }
    }

    project.plugins.withId("org.jetbrains.kotlin.jvm") {
        logger.info("SPOTLESS: configuring Kotlin formatting to ${project.name} (ktlint)")
        kotlin {
            /* detekt or diktat would be nice to use for analysis,
             but only ktlint is supported in both spotless and trunk. */

            target(
                listOf(
                    "**/src/**/*.kt",
                ),
            )

            ktlint()
            licenseHeaderFile(rootProject.file("gradle/LICENSE_HEADER_JAVA"), "package ")
            toggleOffOn()
        }
    }

    project.plugins.withId("scala") {
        logger.info("SPOTLESS: configuring scala formatting to ${project.name} (scalafmt)")
        scala {
            val scalaFmtVersion: String by project
            // version and configFile are both optional
            scalafmt(scalaFmtVersion).configFile(rootProject.file(".scalafmt.conf"))
            target("**/src/**/*.scala")
            licenseHeaderFile(project.rootProject.file("gradle/LICENSE_HEADER_JAVA"), "package ")
        }
    }

    project.plugins.withId("java") {
        logger.info("SPOTLESS: configuring java formatting to ${project.name} (googleJavaFormat)")
        java {
            googleJavaFormat("1.25.2")
                .aosp()
                .reflowLongStrings()
                .formatJavadoc(true)
                .reorderImports(true)
                .groupArtifact("com.google.googlejavaformat:google-java-format")

            target("**/src/**/java/*.java")
            licenseHeaderFile(project.rootProject.file("gradle/LICENSE_HEADER_JAVA"), "package ")
        }
    }
}

tasks.withType<SpotlessTask> {
    // unsure why this is necessary
    // possibly due to a non-standard project layout or additional source sets?
    if (project.plugins.hasPlugin("java")) {
        logger.info("explicitly adding java compile task dependency to spotless task ${this.name}")
        tasks.first { it == this }.mustRunAfter(tasks.withType<JavaCompile>())
    }
}

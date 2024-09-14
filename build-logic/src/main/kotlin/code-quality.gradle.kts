import io.truthencode.djaxonomy.etc.RecurseValue
import io.truthencode.djaxonomy.etc.Recursion

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

//    id("com.github.hierynomus.license")
    id("com.diffplug.spotless")
    id("djaxonomy.quality-sonar")
//    id("com.javiersc.gradle.plugins.dependency.updates")
}

/* Licensing
 go-to license formatter seems to be stale
 https://github.com/KyoriPowered/indra/wiki/indra-licenser-spotless adds improvement wrapper on spotless (i.e. template replacement)
 but currently only supports kotlin, java (no explicit scala)
*/
// indraSpotlessLicenser {
//    licenseHeaderFile(rootProject.file("license_header.txt")) // default value
//    headerFormat { doubleSlash() } // default: slashStar()
//    languageFormatOverride("kotlin") { prefix("/// ") } // default: unset, optional
//    property("name", project.name) // replace $name in the header file with the provided value
//    newLine(false) // default value, adds a blank line between the license header and delimiter
//    extraConfig {
//        // configre options provided by Spotless itself
//    }
// }
// license {
//    header = rootProject.file("gradle/LICENSE_HEADER")
// }

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

val headerFile: File? = rootProject.file("gradle/LICENSE_HEADER_SPOTLESS")

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
//   ratchetFrom("origin/master")
    if (buildLang() == ScriptLanguage.KotlinScriptBuild) {
        logger.debug("SPOTLESS: configuring kotlin script formatting to ${project.name}")
        kotlinGradle {
//            ktlint("0.50.0")
            diktat("1.2.5").configFile(rootProject.file("diktat-analysis.yml"))
            target("*.build.kts")
        }
    }

    project.plugins.withId("org.jetbrains.kotlin.jvm") {
        logger.debug("SPOTLESS: configuring Kotlin formatting to ${project.name}")
        kotlin {
//                licenseHeader("/* (C) \$YEAR */")
//            if (!headerFile?.exists()!!) {
//                logger.error("NO LICENSE FILE FOUND!!!!!!!!!!!!!!")
//            } else {
//                logger.error("LICENSE FILE EXISTS!!!!!")
//                licenseHeaderFile(headerFile)
//            }
            // ktfmt("0.44") // ('0.30').dropboxStyle()
//            ktlint("0.50.0")
            // version and configFile are both optional
            logger.info("kotlin format: ${project.name}")

            target(
                listOf(
                    "*.kt",
                ),
            )
            val dta = "diktat-analysis.yml"
            walkBack(dta, Recursion.RecurseSome(1)).let { f ->
                diktat("1.2.5").configFile(f)
            }

//            diktat("1.0.1").configFile(rootProject.file("diktat-analysis.yml"))
            licenseHeaderFile(rootProject.file("gradle/LICENSE_HEADER_SPOTLESS"))

            toggleOffOn()
        }
    }

//    if (project.plugins.hasPlugin("scala")) {
//        logger.info("SPOTLESS: configuring scala formatting to ${project.name}")
//        scala {
//            //  licenseHeaderFile( rootProject.file("gradle/LICENSE_HEADER"))
//            target("**/*.scala") // don't format worksheets
//            val scalaFmtVersion: String by project
//            // version and configFile are both optional
//            scalafmt(scalaFmtVersion).configFile(rootProject.file(".scalafmt.conf"))
//            target("*.scala")
//        }
//        licenseHeaderFile(rootProject.file("gradle/LICENSE_HEADER_SPOTLESS"))
//    }

    project.plugins.withId("scala") {
        logger.debug("SPOTLESS: configuring scala formatting to ${project.name}")
        scala {
            val scalaFmtVersion: String by project
            // version and configFile are both optional
            scalafmt(scalaFmtVersion).configFile(rootProject.file(".scalafmt.conf"))
            target("*.scala")
            licenseHeaderFile(project.rootProject.file("gradle/LICENSE_HEADER_SPOTLESS"), "package")
        }
    }
}
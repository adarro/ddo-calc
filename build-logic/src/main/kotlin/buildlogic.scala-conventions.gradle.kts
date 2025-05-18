import org.gradle.accessors.dm.LibrariesForLibs

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

//    id("code-quality")
    id("be.vbgn.ci-detect")
    scala
    id("buildlogic.scala-coverage-conventions")
}
val libs = the<LibrariesForLibs>()
val builderScalaVersion: String by project

scala {

    scalaVersion =
        when (builderScalaVersion) {
            "3" -> {
                libs.versions.scala3.version
                    .get()
            }

            else -> {
                libs.versions.scala2.version
                    .get()
            }
        }
}

tasks.withType<ScalaCompile>().configureEach {
    scalaCompileOptions.apply {

        when (builderScalaVersion) {
            "3" -> {
                logger.warn("Scala 3 detected")
                additionalParameters?.plusAssign(
                    listOf(
                        "-feature",
                        "-explain",
                        "-Wsafe-init", // Added per Quarkus - Scala3 extension notes along with semanticdb
                        "-Xsemanticdb",
                        "-semanticdb-target",
                        project.layout.buildDirectory
                            .get()
                            .toString(),
                        "-Yretain-trees", // Needed for Enumeratum
                        "-rewrite",
//                        "-new-syntax",
                        "-source:3.4-migration",
                        "-Xignore-scala2-macros",
                        "-new-syntax",
//                        "explain"
                    ),
                )
            }

            "2" -> {
                logger.warn("Scala 2 detected")
                additionalParameters?.plusAssign(
                    listOf(
                        "-feature",
                        "-deprecation",
                        "-Ywarn-dead-code",
                        "-Xsource:3-cross",
                    ),
                )
            }

            else -> {
                logger.error("Scala version $builderScalaVersion not supported")
            }
        }
    }
}

import io.truthencode.buildlogic.FALLBACK_SCALA_VERSION
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
    //  java // apply (false)
    id("org.scoverage")
}
val libs = the<LibrariesForLibs>()

interface ScalaBuildExtension {
    val scalaVersion: Property<String>
}

val scalaBuildExtension = extensions.create<ScalaBuildExtension>("scalaBuildInfo")

scalaBuildExtension.scalaVersion.convention(
    providers
        .gradleProperty("builderScalaVersion")
        .map { bv ->
            when (bv) {
                "3" -> "3"
                "2" -> "2"
                else -> FALLBACK_SCALA_VERSION
            }
        }.orElse(FALLBACK_SCALA_VERSION),
)
val scalaBaseVersion =
    scalaBuildExtension.scalaVersion
        .flatMap { sv ->
            when (sv) {
                "3" -> {
                    libs.versions.scala3.version
                }

                else -> {
                    libs.versions.scala2.version
                }
            }
        }

scala {
    scalaVersion = scalaBaseVersion
}

configure<org.scoverage.ScoverageExtension> {

//    logger.debug("${project.name} (scoverage) $builderScalaVersion")
//    scoverageVersion.set(libs.versions.scoverage.engine)
    val cfgs =
        mapOf(
            Pair(org.scoverage.CoverageType.Branch, 0.5.toBigDecimal()),
            Pair(org.scoverage.CoverageType.Statement, 0.75.toBigDecimal()),
        ).map { p ->
            val cfg = org.scoverage.ScoverageExtension.CheckConfig()
            cfg.setProperty("coverageType", p.key)
            cfg.setProperty("minimumRate", p.value)
            cfg
        }
    checks.plusAssign(cfgs)
}

afterEvaluate {

    tasks.withType<ScalaCompile>().configureEach {
        // test if refactoring to not use the Scala.apply affects anything
        val cName = this.name
        var opts: List<String> = emptyList()

        val tp =
            layout.buildDirectory
                .dir("semanticdb")
                .get()
                .asFile.path
        logger.debug("Setting target semanticdb root to $tp for configuration $cName")

        val s2Sdb =
            listOf(
                "-Xplugin-require:semanticdb",
                "-P:semanticdb:targetroot:$tp",
            )
        val s3Sdb =
            listOf(
                "-Xsemanticdb",
                "-semanticdb-target:$tp",
            )

        val s3Rewrites =
            listOf(
                "-rewrite",
                "-source:3.4-migration",
                "-Xignore-scala2-macros",
                "-new-syntax",
            )

        val s2LocalDebug = listOf( "-feature",
            "-deprecation",
            "-Ywarn-dead-code",)

        val s3LocalDebug = listOf("-feature",
            "-explain",
        )

        val configuredScalaVersion = scalaBuildExtension.scalaVersion.get()
        logger.debug("${project.name}:$cName Scala Version: $configuredScalaVersion")

        when (configuredScalaVersion) {
            "3" -> {
                opts = listOf(
                    "-Wsafe-init",
                    "-Yretain-trees",
                ) + s3Rewrites // + s3Sdb
                scalaCompileOptions.additionalParameters?.plusAssign(
                    opts,
                )
            }

            "2" -> {
                opts =
                    listOf(
                        "-Xsource:3-cross",
                    ) // + s2Sdb
                scalaCompileOptions.additionalParameters?.plusAssign(
                    opts,
                )
            }

            else -> {
                logger.error("Scala version $configuredScalaVersion not supported")
            }
        }

        logger.warn("$cName ScalaCompile Options: $opts")
    }
} // afterEvaluate

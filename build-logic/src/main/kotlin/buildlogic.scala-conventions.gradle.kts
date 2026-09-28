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
//    id("io.github.cosmicsilence.scalafix")
}
val libs = the<LibrariesForLibs>()

interface ScalaBuildExtension {
    /**
     * The scala major version to use for the project.
     * Expects a value of 2 or 3
     */
    val scalaVersion: Property<String>

    /**
     * Whether to enable rewrite mode for scala 3.
     */
    val rewrite: Property<Boolean>

    /**
     * Whether to enable semanticdb
     * Toggle this to enable semanticdb injection.
     *
     * @note This may need to be adjusted when used in combination with scalafix.
     * Specifically, the scalafix autoconfigure option may need to be disabled.
     */
    val semanticdb: Property<Boolean>
}

val scalaBuildExtension = extensions.create<ScalaBuildExtension>("scalaBuildInfo")

// extension defaults
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

scalaBuildExtension.rewrite.convention(false)
scalaBuildExtension.semanticdb.convention(false)

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

// removed until we can work out sematicdb injection

// TODO: Add scalafix with semanticdb injection
//scalafix {
////    configFile = file("config/myscalafix.conf")
//    includes = listOf("/io/truthencode/**/*.scala")
////    excludes = ["**/generated/**"]
//    ignoreSourceSets = listOf("scoverage")
//        semanticdb {
//        autoConfigure = true
//    }
//}


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
                "-Ysemanticdb",
                "-semanticdb-target:$tp",
            )

        val s3Rewrites =
            listOf(
                "-rewrite",
                "-source",
                "3.6-migration",
                "-Xignore-scala2-macros",
                "-new-syntax",
            )

        val s2LocalDebug =
            listOf(
                "-feature",
                "-deprecation",
                "-Ywarn-dead-code",
            )

        val s3LocalDebug =
            listOf(
                "-feature",
                "-explain",
            )

        val configuredScalaVersion = scalaBuildExtension.scalaVersion.get()
        logger.debug("${project.name}:$cName Scala Version: $configuredScalaVersion")

        when (configuredScalaVersion) {
            "3" -> {
                opts = listOf(
                    "-Wsafe-init",
                    "-Yretain-trees", "-Wunused:all"
                )
                if (scalaBuildExtension.rewrite.getOrElse(false)) {
                    opts.plus(s3Rewrites)
                }
                if (scalaBuildExtension.semanticdb.getOrElse(false)) {
                    opts.plus(s3Sdb)
                }

                scalaCompileOptions.additionalParameters?.plusAssign(
                    opts,
                )
            }

            "2" -> {
                opts =
                    listOf(
                        "-Xsource:3-cross",
                    )
                if (scalaBuildExtension.semanticdb.getOrElse(false)) {
                    opts.plus(s2Sdb)
                }
                scalaCompileOptions.additionalParameters?.plusAssign(
                    opts,
                )
            }

            else -> {
                logger.error("Scala version $configuredScalaVersion not supported")
            }
        }

        logger.debug("{} ScalaCompile Options: {}", cName, opts)
    }
} // afterEvaluate


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
val builderScalaVersion: String by project

dependencies {
    when (builderScalaVersion) {
        "3" -> {

            implementation(libs.scala3.library)

        }

        else -> {

            implementation(libs.scala2.library)

        }


    }


//    val scalaLibraryVersion: String by project
//    val scalaMajorVersion: String by project
//    val scalaCompilerPlugin by configurations.creating
//    scalaCompilerPlugin("com.typesafe.genjavadoc:genjavadoc-plugin_$scalaLibraryVersion:0.18")
//     compileOnly("org.scoverage:scalac-scoverage-plugin_$scalaMajorVersion.7:1.4.10")
//
}

configure<org.scoverage.ScoverageExtension> {
    scoverageVersion.set(libs.versions.scoverage.plugin)
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

tasks.withType<ScalaCompile>().configureEach {
    scalaCompileOptions.apply {

        when(builderScalaVersion) {
            "3" -> {
                logger.warn("Scala 3 detected")
                additionalParameters?.plusAssign(
                    listOf(
                        "-Yretain-trees", // Needed for Enumeratum
                        "-rewrite",
//                        "-new-syntax",
                        "-source:3.4-migration",
                        "-Xignore-scala2-macros",
                        "-new-syntax",
//                        "explain"
                    ))
            }
            "2" -> {
                logger.warn("Scala 2 detected")
                additionalParameters?.plusAssign(
                    listOf(
                        "-feature", "-deprecation", "-Ywarn-dead-code",
                        "-Xsource:3-cross",
                    )
                )
            }
            else -> {
                logger.error("Scala version $builderScalaVersion not supported")
            }
        }
    }
}

//tasks.withType<ScalaCompile>().forEach() { t ->
//    {
//        t.apply {
//            doFirst {
//                println("ScalaCompile: First $t")
//            }
//            doLast {
//                println("ScalaCompile: Last $t")
//
//            }
//
//        }
//    }
//}

//
//
// tasks.withType<ScalaCompile>().configureEach {
//
//    doLast {
//
//        val co = makeOptions(project, "last")
//        val msg = co.joinToString { it }
//        logger.info("derived compiler options\n $msg")
//    }
//
//    tryFindScalaVersionDependencies(project, "inConfigure")
//    this.project // scala-library
//    scalaCompileOptions.apply {
// //        val scalaCompilerPlugin by configurations.getting
//        val scalaCoptions = listOf(
//            "-feature", "-deprecation", "-Ywarn-dead-code",
//            "-Xsource:3"
//        )
//
//        additionalParameters?.plusAssign(
//            scalaCoptions
//        )
//        val rewriteOption = project.findProperty("s3rewrite")?.toString() ?: ""
//        val mo = migrationOptions(rewriteOption)
//
//        logger.warn(" Migration options ${mo.opt}")
//        logger.debug("executing scala compile with options\n $scalaCoptions")
//        // Need to add -Ypartial-unification for Tapir
//    }
// }

// tasks {
//    withType(Test::class.java) {
//        systemProperties["concordion.output.dir"] = "${reporting.baseDir}/spec"
//    }
//    logger.info("concordion output directory: *************************************")
//    // Use the built-in JUnit support of Gradle.
//    "test"(Test::class) {
//        useJUnitPlatform {
//            includeEngines = setOf("scalatest", "vintage")
//            if (ci.isCi)
//                excludeTags = setOf(
//                    "Integration",
//                    "io.truthencode.tags.Integration",
//                    "FunctionOnly",
//                    "io.truthencode.tags.FunctionOnly"
//                )
//            testLogging {
//                events("passed", "skipped", "failed")
//            }
//        }
//
//        outputs.upToDateWhen { false }
//    }
// }
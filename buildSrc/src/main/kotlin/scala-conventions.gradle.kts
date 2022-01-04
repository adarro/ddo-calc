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
    scala

    id("org.scoverage")
    id("code-quality")
    id("be.vbgn.ci-detect")
}


dependencies {
    val scalaLibraryVersion: String by project
    val scalaCompilerPlugin by configurations.creating
    scalaCompilerPlugin("com.typesafe.genjavadoc:genjavadoc-plugin_$scalaLibraryVersion:0.18")
    compileOnly("org.scoverage:scalac-scoverage-plugin_2.13.7:1.4.10")

}


configure<org.scoverage.ScoverageExtension> {
    scoverageVersion.set("1.4.10")
    val cfgs = mapOf(
        Pair(org.scoverage.CoverageType.Branch, 0.5.toBigDecimal()),
        Pair(org.scoverage.CoverageType.Statement, 0.75.toBigDecimal())
    ).map { p ->
        val cfg = org.scoverage.ScoverageExtension.CheckConfig()
        cfg.setProperty("coverageType",p.key)
        cfg.setProperty("minimumRate",p.value)
        cfg
    }
    checks.plusAssign(cfgs)
}

/**
 * Rewrite option should default to '-rewrite' but can be set to '-indent'
 * example ./gradlew ddo-core:scalaCompile -Ps3rewrite=-indent
 * NOTE: this will need to be revisited when not using scala 2.13
 * We should also incorporate the -new-syntax option here instead of below
 */
val rewriteOption = project.findProperty("s3rewrite")?.toString() ?: ""
tasks.withType<ScalaCompile>().configureEach {
    scalaCompileOptions.apply {
        val scalaCompilerPlugin by configurations.getting
        val jDocCompilerPlugin =
            listOf("-P:genjavadoc:out=$buildDir/generated/java", "-Xplugin:${scalaCompilerPlugin.asPath}")
        val scalaCoptions = listOf(
            "-feature", "-deprecation", "-Ywarn-dead-code",
            "-Xsource:3", "-new-syntax"
        ) + listOf(rewriteOption).filter { ww -> ww.isNotBlank() }

        additionalParameters?.plusAssign(
            scalaCoptions
        )
        logger.debug("executing scala compile with options\n $scalaCoptions")
        // Need to add -Ypartial-unification for Tapir
    }
}

tasks.withType<Javadoc> {
    val ss: FileTree = sourceSets["main"].allJava
    val ft = fileTree("$buildDir/generated/java")
    source = ss.plus(ft)

    dependsOn("compileScala")
    // source = listOf(sourceSets.main.allJava, "$buildDir/generated/java")
}


tasks {
    withType(Test::class.java) {
        systemProperties["concordion.output.dir"] = "${reporting.baseDir}/spec"
    }
    logger.info("concordion output directory: *************************************")
    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform {
            includeEngines = setOf("scalatest", "vintage")
            if (ci.isCi)
                excludeTags = setOf("Integration", "io.truthencode.tags.Integration","FunctionOnly","io.truthencode.tags.FunctionOnly")
            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        outputs.upToDateWhen { false }
    }
}
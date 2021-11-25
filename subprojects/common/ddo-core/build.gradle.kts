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
    id("scala-profiles")
    id("acceptance-test-conventions")
}

description = "Core DDO Objects"

dependencies {
    val concordionVersion: String by project

    dependencies {
        implementation(project(":ddo-util"))

        val scalaLibraryVersion: String by project
        val scalaMajorVersion: String by project
        /* Platform dependent */
        // https://mvnrepository.com/artifact/org.json4s/json4s-native
        implementation(group = "org.json4s", name = "json4s-native_$scalaMajorVersion", version = "3.6.7")

        annotationProcessor("net.thauvin.erik:semver:1.2.0")
        compileOnly("net.thauvin.erik:semver:1.2.0")
        implementation(platform(project(":ddo-platform-scala")))
        implementation("org.scala-lang:scala-library:$scalaLibraryVersion")
        implementation(group = "com.beachape", name = "enumeratum_$scalaMajorVersion")
        implementation(group = "com.typesafe", name = "config")
        implementation(group = "com.github.kxbmap", name = "configs_$scalaMajorVersion")
        // validation and rules
        implementation(group = "com.wix", name = "accord-core_$scalaMajorVersion")

        implementation(group = "ch.qos.logback", name = "logback-classic")
        implementation(group = "com.typesafe.scala-logging", name = "scala-logging_$scalaMajorVersion")
        testImplementation(group = "org.scalatest", name = "scalatest_$scalaMajorVersion")
        testImplementation(group = "org.scalacheck", name = "scalacheck_$scalaMajorVersion")
        testImplementation(group = "org.scalatestplus", "mockito-3-4_$scalaMajorVersion")
        testImplementation(group = "com.wix", name = "accord-scalatest_$scalaMajorVersion")

        // JUnit 5
        testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-engine")
        testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-launcher")
        testRuntimeOnly(group = "co.helmethair", name = "scalatest-junit-runner")
        testRuntimeOnly(group = "org.junit.vintage", name = "junit-vintage-engine")

        val scalaCompilerPlugin by configurations.creating
        scalaCompilerPlugin("com.typesafe.genjavadoc:genjavadoc-plugin_$scalaLibraryVersion:0.17")
        // Concordion BDD
        val acceptanceTestImplementation by configurations.getting
        acceptanceTestImplementation.extendsFrom(configurations["testCompileClasspath"])
        acceptanceTestImplementation(group = "org.concordion", name = "concordion", version = concordionVersion)
        acceptanceTestImplementation(
                group = "com.vladsch.flexmark",
                name = "flexmark-ext-gfm-strikethrough",
                version = "0.62.2"
        )
        acceptanceTestImplementation(group = "com.vladsch.flexmark", name = "flexmark-ext-emoji", version = "0.62.2")

        // https://mvnrepository.com/artifact/com.vladsch.flexmark/flexmark-ext-gfm-tasklist
        acceptanceTestImplementation(
                group = "com.vladsch.flexmark",
                name = "flexmark-ext-gfm-tasklist",
                version = "0.62.2"
        )

        testImplementation(group = "de.neuland-bfi", name = "jade4j", version = "1.2.7")
        testImplementation(group = "net.ruippeixotog", name = "scala-scraper_$scalaMajorVersion", version = "2.2.1")
        testCompileOnly(group = "org.jetbrains", name = "annotations", version = "17.0.0")

        implementation(group = "org.jetbrains", name = "annotations", version = "17.0.0")
    }
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
            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        outputs.upToDateWhen { false }
    }
}
tasks.withType<JavaCompile>().configureEach {
    options.annotationProcessorGeneratedSourcesDirectory = file("$projectDir/src/generated/java")
    options.compilerArgs.plusAssign("-Asemver.project.dir=$projectDir")
    dependsOn("syncVersionFiles")
}

/**
 * Rewrite option should default to '-rewrite' but can be set to '-indent'
 * example ./gradlew ddo-core:scalaCompile -Ps3rewrite=-indent
 * NOTE: this will need to be revisited when not using scala 2.13
 * We should also incorporate the -new-syntax option here instead of below
 */
val rewriteOption = project.findProperty("s3rewrite")?.toString() ?: "-rewrite"
tasks.withType<ScalaCompile>().configureEach {
    scalaCompileOptions.apply {
        val scalaCompilerPlugin by configurations.getting
        val scalaCoptions = listOf(
                "-feature", "-deprecation", "-Ywarn-dead-code", "-Xplugin:${scalaCompilerPlugin.asPath}",
                "-P:genjavadoc:out=$buildDir/generated/java", "-Xsource:3", "-new-syntax"
        ) + listOf(rewriteOption).filter {ww -> ww.isNotBlank() }

        additionalParameters?.plusAssign(
                scalaCoptions
        )
        logger.info("executing scala compile with options\n $scalaCoptions")
        // Need to add -Ypartial-unification for Tapir
    }
}
tasks.withType<Javadoc> {
    dependsOn("compileScala")
    val ss: FileTree = sourceSets["main"].allJava
    val ft = fileTree("$buildDir/generated/java")
    source = ss.plus(ft)

    dependsOn("compileScala")
    // source = listOf(sourceSets.main.allJava, "$buildDir/generated/java")
}

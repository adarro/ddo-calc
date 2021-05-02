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
import org.unbrokendome.gradle.plugins.testsets.dsl.testSets

plugins {
    id("org.unbroken-dome.test-sets") // version "2.1.1"
    //  id("scala-profiles")
    // Apply the scala plugin to add support for Scala
    scala

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    id("org.scoverage")
    id("com.github.maiflai.scalatest") // version scalaTestPluginVersion
    //   id("com.github.roroche.plantuml")
}


description = "Core DDO Objects"
testSets {
    "acceptanceTest" {
        dirName = "specs"
        sourceSet.java {
            "acceptanceTest" {
                exclude(
                    "**/*Spec.scala",
                    // "**/*Helper*",
                    "**/*Builder*"
                )
            }
        }


        sourceSet.resources {
            this.srcDirs.add(File("${project.projectDir}/src/test/specs"))
        }
        configurations[compileClasspathConfigurationName].extendsFrom(configurations["testImplementation"])
        createArtifact = true
    }
}

// tasks.getByName("check").dependsOn(tasks.getByName("acceptanceTest"))
dependencies {
    //  val implementation by configurations.getting
    //  val testImplementation by configurations.getting
    //  val scalaLibraryVersion: String by project
    //  val scalaMajorVersion: String by project
    //  val logbackVersion:String by project
//    val scalaLibraryVersion: String by project
//    val scalaMajorVersion: String by project
//    val scalaTestVersion: String by project
//    val scalaCheckVersion: String by project
//    val scalaLoggingVersion: String by project
//    val mockitoVersion: String by project
//    val accordVersion: String by project
//    val scalaScraperVersion: String by project
//    val enumeratumVersion: String by project
//    val typeSafeConfigVersion: String by project
//    val configsVersion: String by project
//    val logbackVersion: String by project
//    val jetBrainsAnnotationVersion: String by project
//    val junitPlatformVersion: String by project
//    val junitPlatformRunnerVersion: String by project
    val concordionVersion: String by project
//    val concordionExtEmbedVersion: String by project
//    val concordionExtCollapseOutputVersion: String by project
//    val scalaFmtVersion: String by project

    dependencies {
        implementation(project(":ddo-util"))
        /* Platform dependent */
        // https://mvnrepository.com/artifact/org.json4s/json4s-native
        implementation(group = "org.json4s", name = "json4s-native_2.12", version = "3.6.7")
        val scalaLibraryVersion: String by project
        val scalaMajorVersion: String by project

        implementation(platform(project(":ddo-platform-scala")))
        implementation("org.scala-lang:scala-library:$scalaLibraryVersion")
        implementation(group = "com.beachape", name = "enumeratum_${scalaMajorVersion}")
        implementation(group = "com.typesafe", name = "config")
        implementation(group = "com.github.kxbmap", name = "configs_${scalaMajorVersion}")
        // validation and rules
        implementation(group = "com.wix", name = "accord-core_2.12")
        implementation(group = "ch.qos.logback", name = "logback-classic")
        implementation(group = "com.typesafe.scala-logging", name = "scala-logging_${scalaMajorVersion}")
        testImplementation(group = "org.scalatest", name = "scalatest_$scalaMajorVersion")
        testImplementation(group = "org.scalacheck", name = "scalacheck_$scalaMajorVersion", version = "1.14.0")
        testImplementation(group = "org.mockito", name = "mockito-all")

        // JUnit 5
        testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-engine")
        testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-launcher")
        testRuntimeOnly(group = "co.helmethair", name = "scalatest-junit-runner")
        testRuntimeOnly(group = "org.junit.vintage", name = "junit-vintage-engine")

        // Concordion BDD
        val acceptanceTestImplementation by configurations.getting
        acceptanceTestImplementation.extendsFrom(configurations["testCompileClasspath"])
        acceptanceTestImplementation(group = "org.concordion", name = "concordion", version = concordionVersion)
        acceptanceTestImplementation( group= "com.vladsch.flexmark", name= "flexmark-ext-gfm-strikethrough", version= "0.62.2")
        acceptanceTestImplementation( group= "com.vladsch.flexmark", name= "flexmark-ext-emoji", version= "0.62.2")

//        acceptanceTestImplementation(group = "org.concordion", name = "concordion-embed-extension", version = "1.2.0")
//        acceptanceTestImplementation(
//            group = "org.concordion",
//            name = "concordion-collapse-output-extension",
//            version = "1.0.0"
//        )
        // https://mvnrepository.com/artifact/com.vladsch.flexmark/flexmark-ext-gfm-tasklist
        acceptanceTestImplementation( group= "com.vladsch.flexmark", name= "flexmark-ext-gfm-tasklist", version= "0.62.2")

        testImplementation(group = "com.wix", name = "accord-scalatest_2.12", version = "0.7.3")
        testImplementation(group = "de.neuland-bfi", name = "jade4j", version = "1.2.7")
        testImplementation(group = "net.ruippeixotog", name = "scala-scraper_2.12", version = "2.1.0")
        testCompileOnly(group = "org.jetbrains", name = "annotations", version = "17.0.0")

        implementation(group = "com.geirsson", name = "scalafmt-core_2.12", version = "1.6.0-RC4-11-9e33ebdb")

        implementation(group = "com.wix", name = "accord-core_2.12", version = "0.7.3")
        implementation(group = "org.jetbrains", name = "annotations", version = "17.0.0")
    }
}

//classDiagram {
//        packageName = "io.truthencode.ddo"
//        outputFile = project.file("diagrams/class_diagram.plantuml")
//
//    }

//tasks.withType(Test::class.java) {
//    // fails with Could not set unknown property 'concordion.output.dir' for task ':complete:test' of type org.gradle.api.tasks.testing.Test.
//    // systemProperties().setProperty("concordion.output.dir", "$reporting.baseDir/spec") // fails
//    // Explicit put appears to work
//    // systemProperties.put("concordion.output.dir", "${reporting.baseDir}/spec") // works
//    useJUnitPlatform {
//        includeEngines = setOf("scalatest", "vintage")
//        testLogging {
//            events("passed", "skipped", "failed")
//        }
//    }
//    systemProperties["concordion.output.dir"] = "${reporting.baseDir}/spec"
//    testLogging.showStandardStreams = true
//    outputs.upToDateWhen { false }
//
//}

// works for running tests but does not respect concordion output dir (defaults to java.tmp.dir)
//tasks {
//    logger.info("concordion output directory: *************************************")
//    // Use the built-in JUnit support of Gradle.
//    "test"(Test::class) {
//        systemProperties["concordion.output.dir"] = "${reporting.baseDir}/spec"
//        useJUnitPlatform {
//            includeEngines = setOf("scalatest", "vintage")
//            testLogging {
//                events("passed", "skipped", "failed")
//            }
//        }
//
//        outputs.upToDateWhen { false }
//    }
//}

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

tasks.withType<ScalaCompile>().configureEach {

    scalaCompileOptions.apply {

        additionalParameters?.plusAssign(listOf("-feature", "-deprecation", "-Ywarn-dead-code"))
        // Need to add -Ypartial-unification for Tapir
    }
}


/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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

description = "Common misc String and convenience Utilities"


plugins {
    id("scala-profiles")

//    scala
//    `java-library` // cross-compiler is incompatible with java-library ATM
//    id("com.github.maiflai.scalatest") // version "0.25"
//    id("org.scoverage") // version "3.1.5"
//    // IDE Specific
// //   idea
//    id("org.unbroken-dome.test-sets")
}

// import common scala project dependencies etc
project.apply { from(rootProject.file("gradle/scala.gradle.kts")) }

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

dependencies {
    val implementation by configurations.getting
    val testImplementation by configurations.getting
    //  val scalaLibraryVersion: String by project
    //  val scalaMajorVersion: String by project
    //  val logbackVersion:String by project
    val scalaLibraryVersion: String by project
    val scalaMajorVersion: String by project
    val scalaTestVersion: String by project
    val scalaCheckVersion: String by project
    val scalaLoggingVersion: String by project
    val mockitoVersion: String by project
    val accordVersion: String by project
    val scalaScraperVersion: String by project
    val enumeratumVersion: String by project
    val typeSafeConfigVersion: String by project
    val configsVersion: String by project
    val logbackVersion: String by project
    val jetBrainsAnnotationVersion: String by project
    val junitPlatformVersion: String by project
    val junitPlatformRunnerVersion: String by project
    val concordionVersion: String by project
    val concordionExtEmbedVersion: String by project
    val concordionExtCollapseOutputVersion: String by project
    val scalaFmtVersion: String by project

    dependencies {
        /* Platform dependent */
        implementation(platform(project(":ddo-platform-scala")))
//        implementation(name = "scala-library", group = "org.scala-lang")
//        implementation(group = "ch.qos.logback", name = "logback-classic")
//        implementation(group = "com.typesafe.scala-logging", name = "scala-logging_${scalaMajorVersion}")
//        testImplementation("org.scalatest:scalatest_$scalaMajorVersion:$scalaTestVersion")
//        testImplementation("org.mockito:mockito-all:$mockitoVersion")

        /* apply from script include */
//        implementation("org.scala-lang:scala-library:$scalaLibraryVersion")
//        implementation("ch.qos.logback:logback-classic:$logbackVersion")
//        implementation("com.typesafe.scala-logging:scala-logging_${scalaMajorVersion}:$scalaLoggingVersion")
//        testImplementation(group = "org.scalatest", name = "scalatest_$scalaMajorVersion")
//        testImplementation(group = "org.mockito", name = "mockito-all")

/* Property include */

        /* standard declaration */
//        implementation(name = "scala-library", group = "org.scala-lang", version = "2.12")
//        api(group = "com.typesafe.scala-logging", name = "scala-logging_2.12", version = "3.9.2")
//        implementation(group = "ch.qos.logback", name = "logback-classic", version = "1.2.3") //,optional
//
//        testImplementation(group = "org.scalatest", name = "scalatest_2.12", version = "3.2.0-SNAP10")
//        testImplementation(group = "org.mockito", name = "mockito-all", version = "2.0.2-beta")


    }
}

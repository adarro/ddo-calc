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

description = "Common misc String and convenience Utilities"

plugins {
    id("scala-library-profile")
//    id("acceptance-test-conventions")
    scala
}
val scalaLibraryVersion: String by project
dependencies {
    dependencies {
        val scalaLibraryVersion: String by project
        val scalaMajorVersion: String by project

        implementation(platform(project(":ddo-platform-scala")))
        // Platform dependent
        // https://mvnrepository.com/artifact/org.json4s/json4s-native
//        implementation(group = "org.json4s", name = "json4s-native_$scalaMajorVersion")
//        implementation(group = "org.scala-lang", name = "scala-library")
        implementation("org.scala-lang:scala-library:$scalaLibraryVersion")

        implementation(group = "com.beachape", name = "enumeratum_$scalaMajorVersion")
//        implementation(group = "com.typesafe", name = "config")
//        implementation(group = "com.github.kxbmap", name = "configs_$scalaMajorVersion")
//        // validation and rules
//        implementation(group = "com.wix", name = "accord-core_$scalaMajorVersion")
        implementation(group = "ch.qos.logback", name = "logback-classic")
        implementation(group = "com.typesafe.scala-logging", name = "scala-logging_$scalaMajorVersion")
//        testImplementation(group = "org.scalatest", name = "scalatest_$scalaMajorVersion")
//        testImplementation(group = "org.mockito", name = "mockito-core")
//
//        // JUnit 5
//        testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-engine")
//        testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-launcher")
//        testRuntimeOnly(group = "co.helmethair", name = "scalatest-junit-runner")
    }
}

// tasks {
//    // Use the built-in JUnit support of Gradle.
//    "test"(Test::class) {
//        useJUnitPlatform {
//            includeEngines = setOf("scalatest")
//            testLogging {
//                events("passed", "skipped", "failed")
//            }
//        }
//    }
//
// }
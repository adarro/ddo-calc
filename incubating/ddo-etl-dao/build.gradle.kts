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
/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Scala library project to get you started.
 * For more details take a look at the Scala plugin chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.1.1/userguide/scala_plugin.html
 */

plugins {
    id("scala-profiles")
    id("acceptance-test-conventions")
}

description = "Async DAO for DDO objects"

dependencies {
    val scalaLibraryVersion: String by project
    val scalaMajorVersion: String by project
    val quillVersion: String by project
    val monixVersion: String by project

    // https://mvnrepository.com/artifact/org.json4s/json4s-native
    implementation(group = "org.json4s", name = "json4s-native_$scalaMajorVersion", version = "3.6.7")

    implementation(platform(project(":ddo-platform-scala")))
    implementation(project(":ddo-modeling"))
    implementation("org.scala-lang:scala-library:$scalaLibraryVersion")
    implementation(group = "com.beachape", name = "enumeratum_$scalaMajorVersion")
    implementation(group = "com.typesafe", name = "config")
    implementation(group = "com.github.kxbmap", name = "configs_$scalaMajorVersion")

    // Quill  / Monix async
    implementation(group = "io.getquill", name = "quill-sql_$scalaMajorVersion", version = quillVersion)
    implementation(group = "io.getquill", name = "quill-core_$scalaMajorVersion", version = quillVersion)
    implementation(group = "io.getquill", name = "quill-monix_$scalaMajorVersion", version = quillVersion)
    implementation(group = "io.getquill", name = "quill-jdbc-monix_$scalaMajorVersion", version = quillVersion)
    // Unsure if I really need this but occasionally get a noclass def for ZIO/Fail
    // https://mvnrepository.com/artifact/dev.zio/zio
    implementation("dev.zio:zio_2.13:1.0.12")

    implementation(group = "io.monix", name = "monix-eval_$scalaMajorVersion", version = monixVersion)
    implementation(group = "io.monix", name = "monix-reactive_$scalaMajorVersion", version = monixVersion)

    // Testing Database
    testImplementation("com.h2database:h2:1.4.200")

    // validation and rules
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
}

tasks {
    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform {
            includeEngines = setOf("scalatest")
            testLogging {
                events("passed", "skipped", "failed")
            }
        }
    }
}

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
    id("scala-conventions")
//    id("acceptance-test-conventions")
    id("djaxonomy.test-conventions")
}

description = "Common ETL module for storing / loading data from web / user etc"

dependencies {
    /*
    might use  https://github.com/nrinaudo
     for etl regex support
     xpath and csv also

     Also, monocle for idiomatic updating of immutable objects (i.e. case classes)
     https://www.optics.dev/Monocle/
     (Need scala 3 to update optional fields and single element in list)
     */
    val scalaLibraryVersion: String by project
    val scalaMajorVersion: String by project
    // https://mvnrepository.com/artifact/org.json4s/json4s-native
    implementation(group = "org.json4s", name = "json4s-native_$scalaMajorVersion", version = "3.6.7")
    implementation(platform(project(":ddo-platform-scala")))
    implementation("org.scala-lang:scala-library:$scalaLibraryVersion")
    implementation(group = "com.beachape", name = "enumeratum_$scalaMajorVersion")
    implementation(group = "com.typesafe", name = "config")
    implementation(group = "com.github.kxbmap", name = "configs_$scalaMajorVersion")

    /* DB, Query etc
    // Quill Scala Query QSQL
     https://github.com/getquill/quill */
    implementation("io.getquill:quill-core_$scalaMajorVersion:3.11.0")
    /* Quill - Monix Integration
    Monix Task / Eval https://monix.io/docs/current/intro/hello-world.html */
    implementation("io.getquill:quill-monix_$scalaMajorVersion:3.11.0")
    implementation("io.getquill:quill-sql_$scalaMajorVersion:3.11.0")
    implementation("io.monix:monix-eval_$scalaMajorVersion:3.4.0")
    implementation("io.monix:monix-reactive_$scalaMajorVersion:3.4.0")

    // validation and rules
    implementation(group = "com.wix", name = "accord-core_$scalaMajorVersion")
    implementation(group = "ch.qos.logback", name = "logback-classic")
    implementation(group = "com.typesafe.scala-logging", name = "scala-logging_$scalaMajorVersion")
//    testImplementation(group = "org.scalatest", name = "scalatest_$scalaMajorVersion")
//    testImplementation(group = "org.scalacheck", name = "scalacheck_$scalaMajorVersion", version = "1.14.0")
//    testImplementation(group = "org.mockito", name = "mockito-core")
//
//    // JUnit 5
//    testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-engine")
//    testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-launcher")
//    testRuntimeOnly(group = "co.helmethair", name = "scalatest-junit-runner")
//    testRuntimeOnly(group = "org.junit.vintage", name = "junit-vintage-engine")
}
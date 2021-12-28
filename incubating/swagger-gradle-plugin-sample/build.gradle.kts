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
    id("scala-library-profile")
    id("openapi-conventions")
}

// sourceCompatibility = 1.8

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compileOnly(group = "org.apache.commons", name = "commons-lang3", version = "3.7")
    compileOnly(group = "javax.ws.rs", name = "javax.ws.rs-api", version = "2.1")
    compileOnly(group = "javax.servlet", name = "javax.servlet-api", version = "3.1.0")
    compileOnly("io.vertx:vertx-web-openapi:4.2.1")
    implementation("io.vertx:vertx-web:4.2.1")
    implementation("io.vertx:vertx-core:4.2.1")

    implementation("io.swagger.core.v3:swagger-annotations:2.1.11")

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

    // did not include wix accord implementation
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

    testImplementation(group = "de.neuland-bfi", name = "jade4j", version = "1.2.7")
    testImplementation(group = "net.ruippeixotog", name = "scala-scraper_$scalaMajorVersion", version = "2.2.1")
    testCompileOnly(group = "org.jetbrains", name = "annotations", version = "17.0.0")

    implementation(group = "org.jetbrains", name = "annotations", version = "17.0.0")
    // TOAD depends
    // Template engines
    // https://mvnrepository.com/artifact/org.scalatra.scalate/scalate-core
    implementation("org.scalatra.scalate:scalate-core_$scalaMajorVersion:1.9.7")
    // https://mvnrepository.com/artifact/org.scalatra.scalate/scalate-test
    testImplementation("org.scalatra.scalate:scalate-test_$scalaMajorVersion:1.9.7")
}

/*
*    outputFileName = "PetStoreAPI"
*    *outputFormat = "JSONANDYAML"
*    outputFormat = "YAML"
*    prettyPrint = "TRUE"
*    classpath = sourceSets.main.runtimeClasspath
*    resourcePackages = ["io.swagger.v3.plugins.grudle.petstore"]
*    outputDir = file(project.buildDir)
*
*
 */

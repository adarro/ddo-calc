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
    `java-platform`
}

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
val junitScalaTestVersion: String by project

dependencies {

    constraints {
        // Must use single string notation, group / name / version will give an error and apply as a dependency
        // api(group = "org.scala-lang", name = "scala-library", version = scalaLibraryVersion)
        api("org.scala-lang:scala-library:$scalaLibraryVersion")
        api("ch.qos.logback:logback-classic:$logbackVersion")
        api("com.typesafe.scala-logging:scala-logging_${scalaMajorVersion}:$scalaLoggingVersion")
        api("org.jetbrains:annotations:$jetBrainsAnnotationVersion")
        api("com.geirsson:scalafmt-core_$scalaMajorVersion:$scalaFmtVersion")
        // Test Dependencies

        // These dependencies, in implementation, should extend some api denoting test implementation, so perhaps create an apiTest / apiAcceptanceTest config?

        // Unit Testing
        api("org.scalatest:scalatest_$scalaMajorVersion:$scalaTestVersion")
        api("org.scalacheck:scalacheck_$scalaMajorVersion:$scalaCheckVersion")

        api("org.mockito:mockito-all:$mockitoVersion")
        api("com.wix:accord-core_${scalaMajorVersion}:${accordVersion}")
        api("com.wix::accord-scalatest_${scalaMajorVersion}$accordVersion")
        api("org.junit.jupiter:junit-jupiter:$junitPlatformVersion")
        // A library providing a DSL for loading and extracting content from HTML pages.
        api("net.ruippeixotog:scala-scraper_$scalaMajorVersion:$scalaScraperVersion")
        api("com.beachape:enumeratum_$scalaMajorVersion:$enumeratumVersion")
        api("com.typesafe:config:$typeSafeConfigVersion")
        api("com.github.kxbmap:configs_${scalaMajorVersion}:$configsVersion")

        // JUnit5
        runtime("co.helmethair:scalatest-junit-runner:$junitScalaTestVersion")
     //   runtime("org.junit.vintage:junit-vintage-engine:$junitPlatformVersion")
        runtime("org.junit.platform:junit-platform-engine:$junitPlatformRunnerVersion")
        api("org.junit.platform:junit-platform-runner:$junitPlatformRunnerVersion")

        // Acceptance Testing 
        // val acceptanceTestImplementation by configurations.getting
        // acceptanceTestImplementation.extendsFrom(configurations["testCompileClasspath"])
        api("org.concordion:concordion:$concordionVersion")
        api("org.concordion:concordion-embed-extension:$concordionExtEmbedVersion")
        api("org.concordion:concordion-collapse-output-extension:$concordionExtCollapseOutputVersion")
    }
}
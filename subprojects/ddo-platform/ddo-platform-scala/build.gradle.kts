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

description = "Project BOM.  Contains common dependencies for scala projects."

dependencies {

    constraints {
        // Must use single string notation, group / name / version will give an error and apply as a dependency
        // api(group = "org.scala-lang", name = "scala-library", version = scalaLibraryVersion)
        api(libs.scala2.library)
        api(libs.logback.classic)
        api(libs.typesafe.scala.logging.s213)

        api(libs.scalafmt.core.s213)
        // Test Dependencies

// Quill https://getquill.io
        api(libs.quill.sql.s213)

        // These dependencies, in implementation, should extend some api denoting test implementation,
        // so perhaps create an apiTest / apiAcceptanceTest config?

        // Unit Testing
        api(libs.scalatest.s213)
        api(libs.scalacheck.s213)
        api(libs.scalatest.plus.mockito.s213)
        api(libs.mockito.core)

        // A library providing a DSL for loading and extracting content from HTML pages.
        api(libs.ruippeixotog.scala.scraper.s213)
        api(libs.enumeratum.s213)
        api(libs.typesafe.config)
        api(libs.kxbmap.configs.s213)

        // json libs
        api(libs.json4s.native.s213)

        // JUnit5
        runtime(libs.scalatest.junit.runner)
        runtime(libs.junit.vintage.engine)
        runtime(libs.junit.platform.engine)
        runtime(libs.junit.platform.launcher)
        api(libs.junit.platform.runner)

        // Acceptance Testing
        // val acceptanceTestImplementation by configurations.getting
        // acceptanceTestImplementation.extendsFrom(configurations["testCompileClasspath"])
        api(libs.concordion)
        api(libs.concordion.collapse.output.extension)
        api(libs.concordion.embed.extension)
    }
}
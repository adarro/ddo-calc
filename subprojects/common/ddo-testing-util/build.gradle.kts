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

description = "Shared Testing and convenience Utilities (Intended for Test Scope)"

plugins {
    id("scala-library-profile")
//    id("djaxonomy.test-conventions")
//    id("acceptance-test-conventions")
}

dependencies {
    dependencies {
        val builderScalaVersion: String by project

        implementation(enforcedPlatform(project(":ddo-platform-scala")))

        logger.error("showing builderScalaVersion: $builderScalaVersion")
        when (builderScalaVersion) {
            "3" -> {
                implementation(libs.scala3.library)
                implementation(libs.enumeratum.s3)
                implementation(libs.typesafe.scala.logging.s3)
                implementation(libs.scalatest.plus.scalacheck.s3)
            }

            else -> {
                implementation(libs.scala2.library)
                implementation(libs.enumeratum.s213)
                implementation(libs.typesafe.scala.logging.s213)
                implementation(libs.scalatest.plus.scalacheck.s213)
            }

        }

        implementation(libs.typesafe.config)
        // No scala 3 specific version ATM
        implementation(libs.kxbmap.configs.s213)


        // database

        // validation and rules
        implementation(libs.logback.classic)


//        implementation(group = "org.scalatest", name = "scalatest_$scalaMajorVersion")

//        testImplementation(group = "org.mockito", name = "mockito-core")
//
//        // JUnit 5
//        testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-engine")
//        testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-launcher")
//        testRuntimeOnly(group = "co.helmethair", name = "scalatest-junit-runner")
    }
}
//tasks {
//    // Use the built-in JUnit support of Gradle.
//    "test"(Test::class) {
//        useJUnitPlatform {
//            includeEngines = setOf("scalatest")
//            testLogging {
//                events("passed", "skipped", "failed")
//            }
//        }
//    }
//}
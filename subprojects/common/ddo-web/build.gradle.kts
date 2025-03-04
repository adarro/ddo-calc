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
    id("buildlogic.scala-library-profile")
    id("buildlogic.test-conventions")
}

description = "DDO Web ETL for wiki data"
dependencies {
    implementation(enforcedPlatform(project(":ddo-platform")))
    implementation(project(":ddo-core"))
    implementation(project(":ddo-modeling"))
    implementation(project(":ddo-util")) {
        because("Implicits with string matching etc")
    }
    val builderScalaVersion: String by project
    logger.info("showing builderScalaVersion: $builderScalaVersion")
    when (builderScalaVersion) {
        "3" -> {
            implementation(libs.scala3.library)
            implementation(libs.ruippeixotog.scala.scraper.s3)
            implementation(libs.enumeratum.s3)
            implementation(libs.typesafe.scala.logging.s3)
        }
        else -> {
            implementation(libs.scala2.library)
            implementation(libs.ruippeixotog.scala.scraper.s213)
            implementation(libs.enumeratum.s213)
            implementation(libs.typesafe.scala.logging.s213)
        }
    }

    implementation(libs.logback.classic)
    implementation(libs.typesafe.config)
}
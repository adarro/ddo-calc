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

// ddo-core
plugins {
    id("scala-library-profile")
    id("djaxonomy.test-conventions")
}

description = "Core DDO Objects"

val concordionVersion: String by project

dependencies {
    implementation(enforcedPlatform(project(":ddo-platform-scala")))
    implementation(project(":ddo-util"))
    implementation(project(":ddo-modeling"))

    // Platform dependent
    // https://mvnrepository.com/artifact/org.json4s/json4s-native
    implementation(libs.json4s.native.s213)
    implementation(libs.scala2.library)
    implementation(libs.enumeratum.s213)
    implementation(libs.typesafe.config)
    implementation(libs.kxbmap.configs.s213)
    // validation and rules
    implementation(libs.wix.accord.core.s213)

    implementation(libs.logback.classic)
    implementation(libs.typesafe.scala.logging.s213)
    testImplementation(project(":ddo-testing-util"))
    implementation(group = "org.jetbrains", name = "annotations", version = "17.0.0")
}

testing {
    suites {
        withType(JvmTestSuite::class).matching { it.name in listOf("acceptanceTest") }.configureEach {
            dependencies {
                implementation(project(":ddo-modeling"))
            }
        }
    }
}
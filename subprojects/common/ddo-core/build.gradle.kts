import com.diffplug.gradle.spotless.SpotlessTask


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
    id("buildlogic.quarkus-component-conventions")
    id("buildlogic.scala-library-profile")
    id("buildlogic.test-conventions")
}

description = "Core DDO Objects"

dependencies {
//    implementation(enforcedPlatform(project(":ddo-platform-scala")))
    implementation(project(":ddo-util"))
    implementation(project(":ddo-modeling"))
    compileOnly(libs.jakarta.inject)
    // https://mvnrepository.com/artifact/org.eclipse.microprofile.config/microprofile-config-api
    implementation(libs.eclipse.microprofile.config)

    // Platform dependent
    // https://mvnrepository.com/artifact/org.json4s/json4s-native
    val builderScalaVersion: String by project
    logger.info("showing builderScalaVersion: $builderScalaVersion")
    when (builderScalaVersion) {
        "3" -> {
            implementation(libs.scala3.library)
            implementation(libs.json4s.native.s3)

            implementation(libs.enumeratum.s3)

            implementation(libs.kxbmap.configs.s213)
            // validation and rules
            // replacing wix accord validation with zio-prelude validation

            implementation(libs.dev.zio.prelude.s3)
            implementation(libs.typesafe.scala.logging.s3)
            testImplementation(libs.dev.zio.test.junit.s3)
        }

        else -> {
            implementation(libs.scala2.library)
            implementation(libs.json4s.native.s213)

            implementation(libs.enumeratum.s213)

            implementation(libs.kxbmap.configs.s213)
            // validation and rules

            implementation(libs.dev.zio.prelude.s213)

            implementation(libs.typesafe.scala.logging.s213)
        }
    }
    implementation(libs.logback.classic)
    implementation(libs.typesafe.config)
    implementation(libs.jetbrains.annotations)
    testImplementation(project(":ddo-testing-util"))
}

testing {
    suites {
        @Suppress("UnstableApiUsage")
        withType(JvmTestSuite::class)
            .matching { it.name in listOf("acceptanceTest") }
            .configureEach {
                dependencies {
                    implementation(project(":ddo-modeling"))
                }
            }
    }
}

//tasks.withType<SpotlessTask> {
//    tasks.first { it == this }.mustRunAfter(tasks.withType<JavaCompile>())
//
//}
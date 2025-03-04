import io.truthencode.buildlogic.KotlinTestKitExtension
import io.truthencode.buildlogic.KotlinTestKits
import io.truthencode.buildlogic.TestBuildSupport
import org.gradle.accessors.dm.LibrariesForLibs

/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2022-2023 Andre White.
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
    id("buildlogic.test-conventions")
    id("buildlogic.kotlin-coverage-conventions")
}

val libs = the<LibrariesForLibs>()

val kte = project.extensions.create<KotlinTestKitExtension>("KotlinTestKitExtension")
kte.useKotlinTestKit.convention(KotlinTestKits.KoTest)
afterEvaluate {

    val testMode =
        findProperty("kotlinTestMode")
            ?.toString()
            ?.let(io.truthencode.buildlogic.TestMode::valueOf)
            ?: io.truthencode.buildlogic.TestMode.REFLECT
    logger.warn("${project.name} kotlinTestMode: $testMode (dependencies)")
    when (testMode) {
        io.truthencode.buildlogic.TestMode.REFLECT -> {
            // Do nothing!
            logger.info("${project.name} using default reflect instead of any annotation processing")
            dependencies {
                testImplementation(libs.squareup.moshi.kotlin)
            }
        }
        io.truthencode.buildlogic.TestMode.KAPT -> {
            apply(plugin = "org.jetbrains.kotlin.kapt")
            dependencies {
                "kaptTest"(libs.squareup.moshi.kotlin.codegen)
            }
            logger.info("${project.name} using kapt")
        }
        io.truthencode.buildlogic.TestMode.KSP -> {
            logger.info("${project.name} using ksp")
            apply(plugin = "com.google.devtools.ksp")
            dependencies {
                "ksp"(libs.squareup.moshi.kotlin.codegen)
                "kspTest"(libs.squareup.moshi.kotlin.codegen)
            }
        }
    }
}

afterEvaluate {
    val testMode =
        findProperty("kotlinTestSuite")
            ?.toString()
            ?.let(KotlinTestKits::valueOf)
            ?: KotlinTestKits.KoTest
    logger.warn("after evaluate ${project.name} kotlinTestMode: $testMode (JvmTestSuite)")
    @Suppress("UnstableApiUsage") // Remove after JvmTestSuite is no longer 'incubating'
    testing {

        val ts = TestBuildSupport(project)

        suites {

            val test =
                when (testMode) {
                    KotlinTestKits.KoTest -> {
                        logger.warn("configuring KoTest for Unit testing (from kts)")
                        val test: JvmTestSuite by getting(JvmTestSuite::class, ts.applyKoTest)
                        test
                    }

                    KotlinTestKits.KotlinTest -> {
                        logger.warn("configuring KotlinTest for Unit testing (from kts)")
                        val test by getting(JvmTestSuite::class) {
                            useKotlinTest()
                        }
                        test
                    }

                    else -> {
                        val test by getting(JvmTestSuite::class)
                        test
                    }
                }

            //        val functionalTest by registering(JvmTestSuite::class) {
            //            dependencies {
            //                implementation(project())
            //            }
            //        }
            //        register<JvmTestSuite>("integrationTest") {
            //            dependencies {
            //                implementation(project())
            //            }
            //
            //            targets {
            //                all {
            //                    testTask.configure {
            //                        shouldRunAfter(t)
            //                    }
            //                }
            //            }
            //        }
        }
    }
}
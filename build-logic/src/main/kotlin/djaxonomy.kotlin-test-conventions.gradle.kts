import io.truthencode.djaxonomy.etc.KotlinTestKitExtension
import io.truthencode.djaxonomy.etc.KotlinTestKits
import io.truthencode.djaxonomy.etc.TestBuildSupport

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
    id("djaxonomy.test-conventions")
    id("djaxonomy.kotlin-coverage-conventions")
}

val koTestVersion: String by project

val kotlinTestMode: io.truthencode.djaxonomy.etc.TestMode by project.extra
val moshiVersion: String by project

val kte = project.extensions.create<KotlinTestKitExtension>("KotlinTestKitExtension")
kte.useKotlinTestKit.convention(KotlinTestKits.KoTest)
afterEvaluate {

    val testMode =
        findProperty("kotlinTestMode")?.toString()
            ?.let(io.truthencode.djaxonomy.etc.TestMode::valueOf)
            ?: io.truthencode.djaxonomy.etc.TestMode.REFLECT
    logger.warn("${project.name} kotlinTestMode: $testMode (dependencies)")
    when (testMode) {
        io.truthencode.djaxonomy.etc.TestMode.REFLECT -> {
            // Do nothing!
            logger.info("${project.name} using default reflect instead of any annotation processing")
            dependencies {
                testImplementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
            }
        }
        io.truthencode.djaxonomy.etc.TestMode.KAPT -> {
            apply(plugin = "org.jetbrains.kotlin.kapt")
            dependencies {
                "kaptTest"("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
            }
            logger.info("${project.name} using kapt")
        }
        io.truthencode.djaxonomy.etc.TestMode.KSP -> {
            logger.info("${project.name} using ksp")
            apply(plugin = "com.google.devtools.ksp")
            dependencies {
                "ksp"("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
                "kspTest"("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
            }
        }
    }
}

// Extensions are just plain objects, there is no interface/type
// class MyExtension(foo: String)

// Add new extensions via the extension container
// val myExt = project.extensions.create("custom", MyExtension::class, "bar")
//                       («name»,   «type»,       «constructor args», …)

// myExt {
//
// }

// val theRealState =
//    theState() // project.extensions.create("theState", TheStateExtension::class) // TheStateExtension.theState()
//
// theRealState {
//    theDeepState {}
//
// }
//
// // 1: DSL-like
// theState {
//    theDeepState {
//        theDeepestState {
//            undermine "the will of the people"
//        }
//    }
// }

// extensions appear as properties on the target object by the given name

afterEvaluate {
    val testMode =
        findProperty("kotlinTestSuite")?.toString()
            ?.let(KotlinTestKits::valueOf)
            ?: KotlinTestKits.KoTest
    logger.warn("${project.name} kotlinTestMode: $testMode (JvmTestSuite)")
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
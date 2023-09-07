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
    id("djaxonomy.java-common-conventions")
    `jvm-test-suite`
}

val extension = project.extensions.create<KotlinTestKitExtension>("KotlinTestKits")
extension.useKotlinTestKit.convention(
    KotlinTestKits.KoTest,
)

project.testing {
    suites {
        apply {
            if (plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
                val ts = TestBuildSupport(project)
                when (extension.useKotlinTestKit.get()) {
                    KotlinTestKits.KoTest -> {
                        logger.warn("configuring KoTest for Unit testing")

                        val test: JvmTestSuite by getting(JvmTestSuite::class, ts.applyKoTest)
                    }

                    KotlinTestKits.KotlinTest -> {
                        logger.warn("configuring KotlinTest for Unit testing")
                        val test by getting(JvmTestSuite::class) {
                            useKotlinTest()
                        }
                    }

                    else -> {
                        val test by getting(JvmTestSuite::class)
                    }
                }
            }
            /*
                   val functionalTest by registering(JvmTestSuite::class) {

            dependencies {
                implementation(project())
            }
        }
        register<JvmTestSuite>("integrationTest") {
            dependencies {
                implementation(project())
          }

            targets {
                all {
                    testTask.configure {
                        shouldRunAfter(t)
                    }
                }
            }
        }

             */
        }
    }
}

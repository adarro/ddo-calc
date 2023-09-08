import io.truthencode.djaxonomy.etc.KotlinTestKitExtension
import io.truthencode.djaxonomy.etc.KotlinTestKits
import io.truthencode.djaxonomy.etc.TestTypes

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

fun JvmTestSuite.applyKoTest() {
    val koTestVersion: String = (findProperty("koTestVersion") ?: embeddedKotlinVersion).toString()


    dependencies {
        implementation("io.kotest:kotest-runner-junit5:$koTestVersion")
        implementation("io.kotest:kotest-assertions-core:$koTestVersion")
        implementation("io.kotest:kotest-property:$koTestVersion")
    }


}

fun JvmTestSuite.applyKotlinTest() {


    useKotlinTest()


}

val junitScalaTestVersion: String by project
val junitPlatformVersion: String by project

fun JvmTestSuite.applyScalaTest() {
    dependencies {
        runtimeOnly("co.helmethair:scalatest-junit-runner:$junitScalaTestVersion")
        runtimeOnly("org.junit.vintage:junit-vintage-engine:$junitPlatformVersion")
    }

    targets.all {
        testTask.configure {
            useJUnitPlatform {
                includeEngines = setOf("scalatest", "vintage")
                testLogging {
                    events("passed", "skipped", "failed")
                }
            }
        }
    }
}

project.testing {
    suites {
        val integrationTest by registering(JvmTestSuite::class)

        val functionalTest by registering(JvmTestSuite::class)
        val performanceTest by registering(JvmTestSuite::class)
        val test by getting(JvmTestSuite::class)
        val acceptanceTest = register<JvmTestSuite>("acceptanceTest")
        configureEach {
            if (this is JvmTestSuite) {
                val tt: TestTypes = TestTypes.fromNamingConvention(name)


// Kotlin specific
                if (project.plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
                    logger.error("Configuring Kotlin Testing for ${project.name}")
                    when (extension.useKotlinTestKit.get()) {
                        KotlinTestKits.KoTest -> {
                            logger.warn("configuring KoTest for Unit testing")
                            test.applyKoTest()
                        }

                        KotlinTestKits.KotlinTest -> {
                            logger.warn("configuring KotlinTest for Unit testing")

                            test.applyKotlinTest()
                        }

                        else -> {}
                    }
                }

                // Scala Specific
                if (project.plugins.hasPlugin("scala")) {

                    logger.error("Configuring ${project.name} for Scala ${tt.name} Testing :  ${this.name} ")

                    when (tt) {
                        TestTypes.Unit -> {
                            logger.error(("Configuring standard Unit Test for scala"))
                            test.applyScalaTest()
                        }

                        TestTypes.Acceptance -> {
                            useJUnitJupiter()
                            logger.error("adding scala acceptance stuff")
                        }

                        else -> {
                            logger.error("no config ATM (applying Jupiter as default")
                            useJUnitJupiter()
                        }

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

        } // config
    }
}
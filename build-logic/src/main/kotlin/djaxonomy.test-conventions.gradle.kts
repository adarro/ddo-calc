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

val scalaLibraryVersion: String by project
val enumeratumVersion: String by project
val logbackVersion: String by project

val scalaTestPlusMockitoVersion: String by project
val scalaCheckVersion: String by project
val scalaTestVersion: String by project
val accordVersion: String by project
val scalaMajorVersion: String by project
val scalaLoggingVersion: String by project

val mockitoVersion: String by project
val concordionVersion: String by project
val flexmarkVersion: String by project
val typeSafeConfigVersion: String by project

fun JvmTestSuite.applyConcordionAcceptanceTest() {
    dependencies {
        implementation(project())
        implementation("org.concordion:concordion:$concordionVersion")
        // flexmark (mostly for concordion / markdown)
        implementation("com.vladsch.flexmark:flexmark-all:$flexmarkVersion") // 0.62.2 -> 0.64.8
//        implementation("com.vladsch.flexmark:ext-gfm-strikethrough:$flexmarkVersion")
//        implementation("com.vladsch.flexmark:flexmark-ext-emoji:$flexmarkVersion")
//        implementation("com.vladsch.flexmark:flexmark-ext-yaml-front-matter:$flexmarkVersion")
//        implementation("com.vladsch.flexmark:flexmark-ext-gfm-tasklist:$flexmarkVersion")
    }
}

fun JvmTestSuite.applyScalaDepends() {
    dependencies {
        implementation("org.scala-lang:scala-library:$scalaLibraryVersion")
        implementation("ch.qos.logback:logback-classic:$logbackVersion")
        implementation("com.typesafe.scala-logging:scala-logging_$scalaMajorVersion:$scalaLoggingVersion")
        implementation("com.beachape:enumeratum_$scalaMajorVersion:$enumeratumVersion")
        implementation("com.typesafe:config:$typeSafeConfigVersion")
    }
}

fun JvmTestSuite.applyVintageEngine() {
    dependencies {
        runtimeOnly("org.junit.vintage:junit-vintage-engine:$junitPlatformVersion")
    }
}

fun JvmTestSuite.applyJupiterEngine() {
    dependencies {
        runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitPlatformVersion")
    }
}

fun JvmTestSuite.applyScalaTest() {
    dependencies {
        runtimeOnly("co.helmethair:scalatest-junit-runner:$junitScalaTestVersion")

        // runtimeOnly("org.junit.vintage:junit-vintage-engine:5.10.0")

        implementation("org.scalatest:scalatest_$scalaMajorVersion:$scalaTestVersion")
        implementation("org.scalacheck:scalacheck_$scalaMajorVersion:$scalaCheckVersion")
        implementation("org.scalatestplus:mockito-3-4_$scalaMajorVersion:$scalaTestPlusMockitoVersion")
        implementation("org.mockito:mockito-core:$mockitoVersion")
        implementation("com.wix:accord-core_$scalaMajorVersion:$accordVersion")
        implementation("com.wix:accord-scalatest_$scalaMajorVersion:$accordVersion")
        // JUnit
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
                            this.applyScalaTest()
                        }

                        TestTypes.Acceptance -> {
                            useJUnitJupiter()
                            this.applyScalaDepends()
                            this.applyJupiterEngine()
                            this.applyVintageEngine()
                            logger.error("adding scala acceptance stuff")
                            dependencies {
                                implementation("de.neuland-bfi:jade4j:1.2.7")
                            }
                            targets.all {
                                testTask.configure {
                                    useJUnitPlatform {
                                        //  includeEngines = setOf("vintage")
                                        //    includeEngines("junit-jupiter", "junit-vintage")
                                        testLogging {
                                            events("passed", "skipped", "failed")
                                        }
                                    }
                                }
                            }
                        }

                        else -> {
                            logger.error("no config ATM (applying Jupiter as default")
                            useJUnitJupiter()
                        }
                    }
                }

                // Concordian BDD Acceptance
                if (tt == TestTypes.Acceptance) {
                    logger.error("applying Concordion Acceptance")
                    this.applyConcordionAcceptanceTest()
                    this.applyVintageEngine()
                    this.applyJupiterEngine()
                }
            }
        }
    }
}
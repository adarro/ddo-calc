@file:Suppress("UnstableApiUsage")

import io.truthencode.buildlogic.KotlinTestKitExtension
import io.truthencode.buildlogic.KotlinTestKits
import io.truthencode.buildlogic.TestTypes
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
import org.gradle.accessors.dm.LibrariesForLibs
import java.util.EnumSet

plugins {
    id("buildlogic.java-common-conventions")
    `jvm-test-suite`
    id("buildlogic.quality-sonar")
}

tasks.withType(Test::class.java) {
    val t = this
    logger.warn("Were in test config for ${project.name}")
    // Jandex dependencies needed here where plugin is applied
    project.plugins.withId("org.kordamp.gradle.jandex") {
        val jandexProjectTask = ":${project.name}:jandex"
        logger.warn("binding project ${project.name} task to $jandexProjectTask")
        t.dependsOn(jandexProjectTask)
    }
    val outDir =
        reports.junitXml.outputLocation
            .get()
            .toString()
    if (t.name.contains("acceptance")) {
        failOnNoDiscoveredTests = false
    }
    systemProperties["concordion.output.dir"] = outDir
    val outputDir = reports.junitXml.outputLocation

//    logger.warn("Setting concordion.output.dir \tto:\t $outDir\nSetting junit.platform.reporting.output.dir \tto: \t${outputDir.get()}")

    val extraProps = mutableListOf("-Djunit.platform.reporting.output.dir=${outputDir.get().asFile.absolutePath}")

    jvmArgumentProviders.add(
        CommandLineArgumentProvider {
            extraProps.plus(
                listOf(
                    "-Djunit.platform.reporting.open.xml.enabled=false", // Legacy format for sonar
                ),
            )
        },
    )
}

sonar {
    properties {
        if (project.plugins.hasPlugin("scala")) {
            val rPath =
                listOf(
                    "scoverageAcceptanceTest/scoverage.xml",
                    "scoverage/scoverage.xml",
                ).joinToString()
            property("sonar.scala.coverage.reportPaths", rPath)
        } else {
            logger.warn(
                "SONAR: ${project.name} is not a scala project or does not have the scala plugin applied, no coverage data will be available",
            )
            // use Jacoco ?
            // sonar.coverage.jacoco.xmlReportPaths
        }
        val junitPaths =
            listOf("test-results/test", "test-results/acceptanceTest").joinToString {
                project.layout.buildDirectory
                    .dir(it)
                    .get()
                    .asFile.path
            }
        property("sonar.junit.reportPaths", junitPaths)
    }
}

val libs = the<LibrariesForLibs>()

/**
 * Extension for configuring Kotlin Test Kit in the project.
 * This extension allows specifying the preferred Kotlin test framework to use.
 * The default test framework is KoTest.
 */
val extension = project.extensions.create<KotlinTestKitExtension>("KotlinTestKits")
extension.useKotlinTestKit.convention(
    KotlinTestKits.KoTest,
)

dependencies {
//     implementation("ognl:ognl:3.4.3")
//     // Extension methods for Java
//     implementation(libs.systems.manifold.preprocessor) {
//         version {
//             because("Concordion indirectly uses old version which interferes with IDE")
// //            strictly("[2023.1.0,[2024.1.34")
//             prefer("2024.1.33")
// //            require("2024.1.33")
// //            reject("2023.1.10")
//         }
//     }

    testRuntimeOnly(libs.junit.platform.reporting)
}

/**
 * Enumeration of project languages supported by the build logic.
 * Each enum constant represents a specific programming language.
 */
enum class ProjectLanguage {
    Kotlin,
    Java,
    Scala,
}

/**
 * Enumeration of language compositions supported by the build logic.
 * Each enum constant represents a specific combination of programming languages in the project.
 */
enum class LanguageComposition {
    KotlinOnly,
    JavaOnly,
    ScalaOnly,
    Mixed,
}

// TODO: Move to Class object under io.truthencode.buildLogic

/**
 * Enumeration of supported test engines for the project.
 * Each enum constant represents a specific test framework and its corresponding ID.
 */
enum class TestEngine(
    val id: String,
) {
    JUnit("junit"),

    /**
     * JUnit6 convention seems to be 'shoehorn' it under JUnit5
     */
    JUnit5("junit-jupiter"),
    JUnit4("junit"),
    Spock("spock"),
    KoTest("kotest"),
    ScalaTest("scalatest"),
    ScalaCheck("scalacheck"),
}

typealias ProjectLanguages = EnumSet<ProjectLanguage>

fun current(): EnumSet<ProjectLanguage>? {
    val pl = ProjectLanguages.noneOf(ProjectLanguage::class.java)
    if (project.plugins.hasPlugin("scala")) {
        pl.add(ProjectLanguage.Scala)
    }
    // TODO: support check for Kotlin Multi Platform
    if (project.plugins.hasPlugin("kotlin")) {
        pl.add(ProjectLanguage.Kotlin)
    }
    if (project.plugins.hasPlugin("java-library") or project.plugins.hasPlugin("java")) {
        pl.add(ProjectLanguage.Java)
    }
    return pl
}

/**
 * Flags if we are using a 'pure' jvm project or mixed (Java + Scala / Kotlin)
 */
fun projectComposition(): LanguageComposition? {
    return current()?.size?.let { entry ->
        return if (entry > 1) {
            LanguageComposition.Mixed
        } else {
            LanguageComposition.entries.find { it.ordinal == current()?.first()?.ordinal }
        }
    }
}

/**
 * Returns a bitmask representation of the project languages.
 * Each language is represented by its ordinal value, and the bits are combined using bitwise OR.
 */
fun ProjectLanguages.projectBits(): Int? = current()?.stream()?.map { it.ordinal }?.reduce(0) { a, b -> a or b }

/**
 * Returns a bitmask representation of the project languages.
 * Each language is represented by its ordinal value, and the bits are combined using bitwise OR.
 */
fun ProjectLanguages.bits(): Int? = this.stream().map { it.ordinal }?.reduce(0) { a, b -> a or b }

/**
 * Applies KoTest dependencies to the JVM test suite.
 */
fun JvmTestSuite.applyKoTest() {
    dependencies {
        implementation(libs.kotest.runner.junit.jvm)
        implementation(libs.kotest.assertions.core)
        implementation(libs.kotest.property)
    }
}

fun JvmTestSuite.applyKotlinTest() {
    useKotlinTest()
}

fun JvmTestSuite.applyConcordionAcceptanceTest() {
    dependencies {
        implementation(project())
        implementation(libs.concordion)
        // flexmark (mostly for Concordion / Markdown)
        implementation(libs.flexmark.all)
    }
}

fun JvmTestSuite.applyScala2Depends() {
    dependencies {
        implementation(libs.scala2.library)
        implementation(libs.logback.classic)
        implementation(libs.typesafe.scala.logging.s213)
        implementation(libs.enumeratum.s213)
        implementation(libs.typesafe.config)
    }
}

fun JvmTestSuite.applyScala3Depends() {
    dependencies {
        implementation(libs.scala3.library)
        implementation(libs.logback.classic)
        implementation(libs.typesafe.scala.logging.s3)
        implementation(libs.enumeratum.s3)
        implementation(libs.typesafe.config)
    }
}

fun JvmTestSuite.applyVintageEngine() {
    dependencies {
        runtimeOnly(libs.junit.vintage.engine)
    }
}

fun JvmTestSuite.applyJupiterEngine() {
    dependencies {
        runtimeOnly(libs.junit.jupiter.engine)
    }
}

fun JvmTestSuite.applyJavaAssertions() {
    dependencies {
        // truth pulls in Android guava unless we explicitly exclude it
        implementation(libs.guava.jre)
        implementation(libs.google.truth)
    }
}

fun JvmTestSuite.applyScalaTest() {
    dependencies {
        // FIXME: need to change this provider to match the ScalaBuildExtension behavior
        val builderScalaVersion = providers.gradleProperty("builderScalaVersion").getOrElse("3")
        // scalatestplus-junit5 is listed as runtimeOnly, but requires implementation for JUnitSuiteLike and JUnitSuite
        if (builderScalaVersion == "3") {
            implementation(libs.scalatest.plus.junit.s3)
            implementation(libs.scalatest.s3)
            implementation(libs.scalatest.plus.mockito.s3)

            implementation(libs.mockito.core)
            implementation(libs.scalatest.plus.scalacheck.s3)
        } else {
            implementation(libs.scalatest.plus.junit.s213)
            implementation(libs.scalatest.s213)
            implementation(libs.scalatest.plus.mockito.s213)

            implementation(libs.mockito.core)
            implementation(libs.scalatest.plus.scalacheck.s213)
        }

// JUnit
    }

//    targets.all {
//        testTask.configure {
//            useJUnitPlatform {
//                includeEngines = setOf("scalatest", "vintage", "jupiter")
//                testLogging {
//                    events("passed", "skipped", "failed")
//                }
//            }
//        }
//    }
}

testing {
    suites {
        /*
        TODO: Add functional / integration etc as needed
        Also need to determine if this is a limited scope (i.e opt in by project)
        integrationTest by registering(JvmTestSuite::class)
        functionalTest by registering(JvmTestSuite::class)
        performanceTest by registering(JvmTestSuite::class)
         */
        val test = named<JvmTestSuite>("test")
        val acceptanceTest = register<JvmTestSuite>("acceptanceTest")
        configureEach {
            if (this is JvmTestSuite) {
                val tt: TestTypes = TestTypes.fromNamingConvention(name)

                // Scala Specific
                if (project.plugins.hasPlugin("scala")) {
                    val builderScalaVersion = providers.gradleProperty("builderScalaVersion").getOrElse("3")
                    logger.debug("Configuring ${project.name} for Scala$builderScalaVersion ${tt.name} Testing :  ${this.name} ")

                    when (tt) {
                        TestTypes.Unit -> {
                            logger.debug(("Configuring standard Unit Test for scala"))
                            useJUnitJupiter()
                            this.applyJupiterEngine()
                            //   this.applyVintageEngine()
                            this.applyScalaTest()

                            targets {
                                logger.debug("Configuring Scala Unit Test for ${project.name}")
                                this.forEach { tg ->
                                    mapOf(tg.name to tg.testTask).forEach { (name, task) ->
                                        logger.warn(
                                            "${project.name} - $name : ${task.name}",
                                        )
                                    }
                                }
                                all {
                                    testTask.configure {
                                        this.filter {
                                            setIncludePatterns("*Test", "*Suite")
                                            setExcludePatterns("*IT", "*Spec")
                                        }
                                        useJUnitPlatform {
                                            includeEngines =
                                                setOf(
                                                    TestEngine.JUnit5.id,
                                                    TestEngine.ScalaTest.id,
                                                )

                                            testLogging {
                                                events("passed", "skipped", "failed")
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        TestTypes.Acceptance -> {
                            useJUnitJupiter()
                            // using scala helper methods in test configuration, so we need to make sure
                            // scala is on the test classpath even if it's a kotlin / java etc. project
                            if (builderScalaVersion == "3") {
                                this.applyScala3Depends()
                            } else {
                                this.applyScala2Depends()
                            }

                            this.applyJupiterEngine()
                            this.applyVintageEngine()
                            logger.debug("adding scala acceptance stuff")
                            dependencies {
                                implementation(libs.jade4j)
                            }
                            targets.all {
                                testTask.configure {
                                    useJUnitPlatform {
                                        testLogging {
                                            events("passed", "skipped", "failed")
                                        }
                                    }
                                }
                            }
                        }

                        else -> {
                            logger.info("no config ATM (applying Jupiter as default")
                            useJUnitJupiter()
                        }
                    }
                    this.applyJavaAssertions()
                } // scala test configuration

                if (project.plugins.hasPlugin("java-library") and (projectComposition() != LanguageComposition.Mixed)) {
                    logger.debug("java-library applied to ${project.name}, applying JUnit Jupiter")
                    useJUnitJupiter()
                    this.applyJavaAssertions()
                }

                // Kotlin configured in its specific convention file and seems functional, need to check before uncommenting below
                // Kotlin specific
//                 if (project.plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
//                     logger.info("Configuring Kotlin Testing for ${project.name}")
//                     when (extension.useKotlinTestKit.get()) {
//                         KotlinTestKits.KoTest -> {
//                             logger.warn("configuring KoTest for Unit testing")
//                             this.applyKoTest()
//                         }
//
//                         KotlinTestKits.KotlinTest -> {
//                             logger.warn("configuring KotlinTest for Unit testing")
//
//                             this.applyKotlinTest()
//                         }
//
//                         else -> {
//                             logger.warn("No specific Kotlin Test for Unit testing specified")
//                         }
//                     }
//                 }

                // Concordian BDD Acceptance
                if (tt == TestTypes.Acceptance) {
                    logger.debug("applying Concordion Acceptance")

                    //  systemProperties["concordion.output.dir"] = "${reporting.baseDir}/spec"
                    this.applyConcordionAcceptanceTest()
                    this.applyVintageEngine()
                    this.applyJupiterEngine()
                }
            } else {
                logger.warn("${this.name} is not a JvmTestSuite, skipping config")
            }
        }
    }
}

// ensure JUnit XML
plugins.withType<JavaPlugin> {
    tasks.withType<Test>().configureEach {
        reports {
            junitXml.required.set(true)
        }
    }
}

// Ensure JaCoCo captures data for both suites
tasks.withType<JacocoReport> {
    dependsOn(testing.suites)
    classDirectories.setFrom(
        files(
            classDirectories.files.map {
                fileTree(it) {
                    include("io/truthencode/**") // Adjust to your package structure
                    exclude("**/META-INF/**")
                }
            },
        ),
    )
}

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
    logger.info("Were in test config for ${project.name}")
    // Jandex dependencies needed here where plugin is applied
    project.plugins.withId("org.kordamp.gradle.jandex") {
        val jandexProjectTask = ":${project.name}:jandex"
        logger.info("binding project ${project.name} task to $jandexProjectTask")
        t.dependsOn(jandexProjectTask)
    }
    systemProperties["concordion.output.dir"] = "${reporting.baseDirectory}/tests"
    val outputDir = reports.junitXml.outputLocation

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

val extension = project.extensions.create<KotlinTestKitExtension>("KotlinTestKits")
extension.useKotlinTestKit.convention(
    KotlinTestKits.KoTest,
)

dependencies {
//     implementation("ognl:ognl:3.4.3")
//     // Extension methods for Java
//     implementation(libs.systems.manifold.preprocessor) {
//         version {
//             because("Concordion indirectly uses old version which interfers with IDE")
// //            strictly("[2023.1.0,[2024.1.34")
//             prefer("2024.1.33")
// //            require("2024.1.33")
// //            reject("2023.1.10")
//         }
//     }

    testRuntimeOnly(libs.junit.platform.reporting)
}

enum class ProjectLanguage {
    Kotlin,
    Java,
    Scala,
}

enum class LanguageComposition {
    KotlinOnly,
    JavaOnly,
    ScalaOnly,
    Mixed,
}

enum class TestEngine(
    val id: String,
) {
    JUnit("junit"),
    JUnit5("junit-jupiter"),
    JUnit4("junit"),
    Spock("spock"),
    KoTest("kotest"),
    ScalaTest("scalatest"),
    ScalaCheck("scalacheck"),
}

typealias ProjectLanguages = EnumSet<ProjectLanguage>

// infix fun ProjectLanguages.or(other: ProjectLanguages): ProjectLanguages = this.stream().map {
//    it.ordinal

fun current(): EnumSet<ProjectLanguage>? {
    val pl = ProjectLanguages.noneOf(ProjectLanguage::class.java)
    if (project.plugins.hasPlugin("scala")) {
        pl.add(ProjectLanguage.Scala)
    }
    if (project.plugins.hasPlugin("kotlin")) {
        pl.add(ProjectLanguage.Kotlin)
    }
    if (project.plugins.hasPlugin("java-library") or project.plugins.hasPlugin("java")) {
        pl.add(ProjectLanguage.Java)
    }
    return pl
}

fun projectComposition(): LanguageComposition? {
    return current()?.size?.let {
        return if (it > 1) {
            LanguageComposition.Mixed
        } else {
            LanguageComposition.values().find { it.ordinal == current()?.first()?.ordinal }
        }
    }
}

fun ProjectLanguages.projectBits(): Int? = current()?.stream()?.map { it.ordinal }?.reduce(0) { a, b -> a or b }

fun ProjectLanguages.bits(): Int? = this.stream().map { it.ordinal }?.reduce(0) { a, b -> a or b }

fun JvmTestSuite.applyKoTest() {
    val koTestVersion: String = (findProperty("koTestVersion") ?: embeddedKotlinVersion).toString()

    dependencies {
        implementation(libs.kotest.runner.junit.jvm)
        implementation("io.kotest:kotest-assertions-core:$koTestVersion")
        implementation("io.kotest:kotest-property:$koTestVersion")
    }
}

fun JvmTestSuite.applyKotlinTest() {
    useKotlinTest()
}

fun JvmTestSuite.applyConcordionAcceptanceTest() {
    dependencies {
        implementation(project())
        implementation(libs.concordion)
// flexmark (mostly for concordion / markdown)
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
        val builderScalaVersion: String by project
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

project.testing {
    suites {
/*
TODO: Add functional / integration etc as needed
Also need to determine if this is a limited scope (i.e opt in by project)
integrationTest by registering(JvmTestSuite::class)
functionalTest by registering(JvmTestSuite::class)
performanceTest by registering(JvmTestSuite::class)
 */
        val test by getting(JvmTestSuite::class)
        val acceptanceTest = register<JvmTestSuite>("acceptanceTest")
        configureEach {
            if (this is JvmTestSuite) {
                val tt: TestTypes = TestTypes.fromNamingConvention(name)

// Kotlin specific
                if (project.plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
                    logger.info("Configuring Kotlin Testing for ${project.name}")
                    when (extension.useKotlinTestKit.get()) {
                        KotlinTestKits.KoTest -> {
                            logger.warn("configuring KoTest for Unit testing")
                            this.applyKoTest()
                        }

                        KotlinTestKits.KotlinTest -> {
                            logger.warn("configuring KotlinTest for Unit testing")

                            this.applyKotlinTest()
                        }

                        else -> {
                            logger.warn("No specific Kotlin Test for Unit testing specified")
                        }
                    }
                }

// Scala Specific
                if (project.plugins.hasPlugin("scala")) {
                    val builderScalaVersion: String by project
                    logger.info("Configuring ${project.name} for Scala$builderScalaVersion ${tt.name} Testing :  ${this.name} ")

                    when (tt) {
                        TestTypes.Unit -> {
                            logger.info(("Configuring standard Unit Test for scala"))
                            useJUnitJupiter()
                            this.applyJupiterEngine()
                            //   this.applyVintageEngine()
                            this.applyScalaTest()

                            targets {
                                logger.warn("Configuring Scala Unit Test for ${project.name}")
                                this.forEach { tg ->
                                    mapOf(tg.name to tg.testTask).forEach { (name, task) ->
                                        logger.warn(
                                            "$name : ${task.name}",
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
                            // using scala helper methods in test configuration so we need to make sure
                            // scala is on the test classpath even if it's a kotlin / java etc project
                            if (builderScalaVersion == "3") {
                                this.applyScala3Depends()
                            } else {
                                this.applyScala2Depends()
                            }

                            this.applyJupiterEngine()
                            this.applyVintageEngine()
                            logger.info("adding scala acceptance stuff")
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
                }
                if (project.plugins.hasPlugin("java-library") and (projectComposition() != LanguageComposition.Mixed)) {
                    logger.info("java-library applied to ${project.name}, applying JUnit Jupiter")
                    useJUnitJupiter()
                    this.applyJavaAssertions()
                }

// Concordian BDD Acceptance
                if (tt == TestTypes.Acceptance) {
                    logger.info("applying Concordion Acceptance")

//  systemProperties["concordion.output.dir"] = "${reporting.baseDir}/spec"
                    this.applyConcordionAcceptanceTest()
                    this.applyVintageEngine()
                    this.applyJupiterEngine()
                }
            }
        }
    }
}
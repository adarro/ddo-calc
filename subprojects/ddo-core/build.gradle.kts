/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
import org.unbrokendome.gradle.plugins.testsets.dsl.testSets

plugins {
    id("org.unbroken-dome.test-sets") // version "2.1.1"
    id("scala-profiles")
    id("com.github.maiflai.scalatest") // version scalaTestPluginVersion
}


description = "Core DDO Objects"
testSets {
    "acceptanceTest" {
        dirName = "specs"
        sourceSet.java {
            "acceptanceTest" {
                exclude(
                    "**/*Spec.scala",
                   // "**/*Helper*",
                    "**/*Builder*"
                )
            }
        }


        sourceSet.resources {
            this.srcDirs.add(File("${project.projectDir}/src/test/specs"))
        }
        configurations[compileClasspathConfigurationName].extendsFrom(configurations["testImplementation"])
        createArtifact = true
    }
}
tasks.getByName("check").dependsOn(tasks.getByName("acceptanceTest"))
dependencies {
    // Each project has a dependency on the platform
    api(platform(project(":ddo-platform")))
    val junitPlatformVersion: String by project
    val junitPlatformRunnerVersion: String by project
    implementation(group = "com.beachape", name = "enumeratum_2.12", version = "1.5.13")
    implementation(group = "com.typesafe", name = "config", version = "1.3.4")
    implementation(group = "com.github.kxbmap", name = "configs_2.12", version = "0.4.4")
    implementation(group = "com.wix", name = "accord-core_2.12", version = "0.7.3")
    implementation(group = "com.typesafe.scala-logging", name = "scala-logging_2.12", version = "3.9.2")
    implementation(group = "ch.qos.logback", name = "logback-classic", version = "1.2.3") //,optional
    implementation(group = "org.jetbrains", name = "annotations", version = "17.0.0")
    testImplementation(group = "org.scalatest", name = "scalatest_2.12", version = "3.2.0-SNAP10")
    testImplementation(group = "org.scalacheck", name = "scalacheck_2.12", version = "1.14.0")


    testImplementation(group = "org.mockito", name = "mockito-all", version = "2.0.2-beta")
    //   testImplementation (group= "junit", name= "junit", version="4.12"
    // Test
    // JUnit 5
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter", version = junitPlatformVersion)
    testRuntimeOnly(group = "org.junit.vintage", name = "junit-vintage-engine", version = junitPlatformVersion)
    testImplementation(
        group = "org.junit.platform",
        name = "junit-platform-runner",
        version = junitPlatformRunnerVersion
    )

    // Concordion BDD
    val acceptanceTestImplementation by configurations.getting
    acceptanceTestImplementation.extendsFrom(configurations["testCompileClasspath"])
    acceptanceTestImplementation(group = "org.concordion", name = "concordion", version = "2.2.0")
    acceptanceTestImplementation(group = "org.concordion", name = "concordion-embed-extension", version = "1.2.0")
    acceptanceTestImplementation(
        group = "org.concordion",
        name = "concordion-collapse-output-extension",
        version = "1.0.0"
    )
    testImplementation(group = "com.wix", name = "accord-scalatest_2.12", version = "0.7.3")
    testImplementation(group = "de.neuland-bfi", name = "jade4j", version = "1.2.7")
    testImplementation(group = "net.ruippeixotog", name = "scala-scraper_2.12", version = "2.1.0")
    testCompileOnly(group = "org.jetbrains", name = "annotations", version = "17.0.0")

    implementation(group = "com.geirsson", name = "scalafmt-core_2.12", version = "1.6.0-RC4-11-9e33ebdb")

}




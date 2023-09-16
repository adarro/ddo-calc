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
//    id("ru.vyarus.mkdocs") // version "3.0.0"
    id("djaxonomy.test-conventions")
//    id("doc-uml")
}

tasks.named("check") {
    dependsOn(testing.suites.named("integrationTest"))
}

description = "Core DDO Objects"


    val concordionVersion: String by project

    dependencies {
        implementation(enforcedPlatform(project(":ddo-platform-scala")))
        implementation(project(":ddo-util"))
        implementation(project(":ddo-modeling"))

        val scalaLibraryVersion: String by project
        val scalaMajorVersion: String by project
        // Platform dependent
        // https://mvnrepository.com/artifact/org.json4s/json4s-native
        implementation(libs.json4s.native.s213)

//        annotationProcessor("net.thauvin.erik:semver:1.2.0")
//        compileOnly("net.thauvin.erik:semver:1.2.0")

        implementation(libs.scala2.library)
        implementation(libs.enumeratum.s213)
        implementation(libs.typesafe.config)
        implementation(group = "com.github.kxbmap", name = "configs_$scalaMajorVersion")
        // validation and rules
        implementation(libs.wix.accord.core.s213)

        implementation(libs.logback.classic)
        implementation(libs.typesafe.scala.logging.s213)
        testImplementation(project(":ddo-testing-util"))
        // testImplementation(group = "org.scalatest", name = "scalatest_$scalaMajorVersion")
        // testImplementation(group = "org.scalacheck", name = "scalacheck_$scalaMajorVersion")
        // testImplementation(group = "org.scalatestplus", "mockito-3-4_$scalaMajorVersion")
        // testImplementation(group = "com.wix", name = "accord-scalatest_$scalaMajorVersion")

        // // JUnit 5
        // testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-engine")
        // testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-launcher")
        // testRuntimeOnly(group = "co.helmethair", name = "scalatest-junit-runner")
        // testRuntimeOnly(group = "org.junit.vintage", name = "junit-vintage-engine")

        // Concordion BDD
//        val acceptanceTestImplementation by configurations.getting
//        acceptanceTestImplementation.extendsFrom(configurations["testCompileClasspath"])
//        acceptanceTestImplementation(group = "org.concordion", name = "concordion", version = concordionVersion)
//        // flexmark (mostly for concordion / markdown)
//        acceptanceTestImplementation("com.vladsch.flexmark:flexmark-all:0.62.2")

//        acceptanceTestImplementation(
//            group = "com.vladsch.flexmark",
//            name = "flexmark-ext-gfm-strikethrough",
//            version = "0.62.2"
//        )
//        acceptanceTestImplementation(group = "com.vladsch.flexmark", name = "flexmark-ext-emoji", version = "0.62.2")
//        acceptanceTestImplementation ("com.vladsch.flexmark:flexmark-ext-yaml-front-matter:0.62.2")
//        // https://mvnrepository.com/artifact/com.vladsch.flexmark/flexmark-ext-gfm-tasklist
//        acceptanceTestImplementation(
//            group = "com.vladsch.flexmark",
//            name = "flexmark-ext-gfm-tasklist",
//            version = "0.62.2"
//        )

        // testImplementation(group = "de.neuland-bfi", name = "jade4j", version = "1.2.7")
        // testImplementation(group = "net.ruippeixotog", name = "scala-scraper_$scalaMajorVersion", version = "2.2.1")
        // testCompileOnly(group = "org.jetbrains", name = "annotations", version = "17.0.0")

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

// sourceSets {
//    this.getByName("acceptanceTest") {
//        java {
//            setSrcDirs(listOf<String>())
//        }
//        scala {
//            setSrcDirs(listOf("test/scala"))
//        }
//    }
// }
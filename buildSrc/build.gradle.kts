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
plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal() // so that external plugins can be resolved in dependencies section
    mavenCentral()
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.0.0")
    implementation("org.unbroken-dome.gradle-plugins:gradle-testsets-plugin:4.0.0")
    implementation("org.scoverage:gradle-scoverage:7.0.0")
    implementation("gradle.plugin.org.dripto.gradle.plugin.plantuml:plugin:0.0.3")
    implementation("com.cosminpolifronie.gradle:gradle-plantuml-plugin:1.6.0")
    implementation("io.swagger.core.v3:swagger-gradle-plugin:2.1.11")
    implementation("gradle.plugin.ms.ralph.gradle:gradle-dependency-plantuml-exporter-plugin:1.0.0")
    implementation("be.vbgn.gradle:ci-detect-plugin:0.5.0")
    implementation("com.javiersc.gradle-plugins:dependency-updates:0.1.0-rc.39")
    implementation("io.spring.gradle:dependency-management-plugin:1.0.11.RELEASE")

//    implementation("io.spring.dependency-management") version "1.0.11.RELEASE"
}

kotlin {
    this.target {
        this.compilations.all {
//            this.kotlinOptions {
//             //   jvmTarget = "11"
//                languageVersion = JavaVersion.VERSION_11.toString()
//            }
        }
    }
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(11)) // "8"
    }

}


// tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
//     this.sourceCompatibility = "11"
//     this.targetCompatibility = "11"
// }

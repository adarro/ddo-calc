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
    gradlePluginPortal()
    google()
    mavenCentral()
    maven("https://jitpack.io")
    maven {
        url = uri("https://repo.orbitalhq.com/release")
    }
}

val kotlinVersion: String by project
val quarkusPlatformVersion: String by project
val jandexPluginVersion: String by project
val defaultJavaToolChainVersion: String? by project
val kasechangeVersion: String by project

dependencies {
    // tool languages
    // node
    implementation("com.github.node-gradle:gradle-node-plugin:7.0.0")
// code quality
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.19.0")
    implementation("com.javiersc.gradle-plugins:dependency-updates:0.1.0-rc.40")
    // doc generation (requires python)
//    implementation("com.palantir.baseline:gradle-baseline-java:$palantirPluginVersion")

    // CI build support
    implementation("be.vbgn.gradle:ci-detect-plugin:0.5.0")

//    implementation("org.unbroken-dome.gradle-plugins:gradle-testsets-plugin:4.0.0")
    // scala
    implementation("org.scoverage:gradle-scoverage:8.0.3")

    // documentation / visualization
    // plant uml
    implementation("io.freefair.gradle:plantuml-plugin:8.2.2")
    implementation("gradle.plugin.org.dripto.gradle.plugin.plantuml:plugin:0.0.3")
    implementation("com.cosminpolifronie.gradle:gradle-plantuml-plugin:1.6.0")

    implementation("io.swagger.core.v3:swagger-gradle-plugin:2.1.11")
    implementation("gradle.plugin.ms.ralph.gradle:gradle-dependency-plantuml-exporter-plugin:1.0.0")
    implementation("io.spring.gradle:dependency-management-plugin:1.0.15.RELEASE")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("com.google.devtools.ksp:symbol-processing-gradle-plugin:$kotlinVersion-1.0.13")
    // not finding jitpacked resource
//    implementation("com.strumenta.antlr-kotlin:antlr-kotlin-gradle-plugin:70d79b7eb1")

    // quarkus related
    implementation("io.quarkus:gradle-application-plugin:$quarkusPlatformVersion")
    implementation("org.kordamp.gradle:jandex-gradle-plugin:$jandexPluginVersion")

    // Database
    implementation("app.cash.sqldelight:gradle-plugin:2.0.0-alpha05")

    // String utils
    // camel / snake etc
    // universal dependency for Gradle 5.3 and above
    // in case of multiplatform project, just specify the dependency for commonMain/commonTest source set
    implementation("net.pearx.kasechange:kasechange:$kasechangeVersion")
    // to here
    //    implementation("com.diffplug.spotless-changelog:spotless-changelog-plugin-gradle:2.4.0")
//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinPluginVersion")
    // implementation("com.palantir.baseline:com.palantir.gradle-baseline-config:$palantirPluginVersion")
    // classpath for  id("com.palantir.baseline")
    //  id("app.cash.sqldelight") version "2.0.0-alpha05"
//    implementation("com.palantir.baseline:com.palantir.baseline-reproducibility:$palantirPluginVersion")
//    implementation("com.palantir.baseline:com.palantir.baseline-config:$palantirPluginVersion")
//    implementation("com.palantir.baseline:com.palantir.baseline-exact-dependencies:$palantirPluginVersion")
//    implementation("com.palantir.baseline:gradle-baseline-java:$palantirPluginVersion")
//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    // im
//
// implementation("app.cash.sqldelight:runtime-jvm:2.0.0-alpha05")
//    implementation("ru.vyarus:gradle-mkdocs-plugin:3.0.0")
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(defaultJavaToolChainVersion ?: "17")) // "8"
        // GraalvmToolchain support doesn't work for CI systems. Circle CI requires custom image since deprecating cicrleci/graal in favor of cimg/openjdk
        // (this as JavaToolchainSpec).vendor.set(JvmVendorSpec.GRAAL_VM)
    }
}
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
    implementation(libs.quarkus.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlin.allopen.plugin)
    implementation(libs.scalafix.plugin)
    implementation(libs.nullaway.plugin)
    implementation(libs.errorprone.plugin)

    implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:_")
    // enable gradle catalog for included convention plugins
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    // tool languages
    // node
    implementation("com.github.node-gradle:gradle-node-plugin:_")
// code quality
    implementation(libs.spotless.plugin)
    implementation(libs.version.plugin)
    implementation(libs.version.catalog.plugin)
//    implementation(libs.refreshVersions.plugin)
    implementation("com.javiersc.gradle-plugins:dependency-updates:_")
    // doc generation (requires python)
//    implementation("com.palantir.baseline:gradle-baseline-java:_")

    // CI build support
    // TODO: Remove ci plugin and use manual script
    implementation("be.vbgn.gradle:ci-detect-plugin:_")

//    implementation("org.unbroken-dome.gradle-plugins:gradle-testsets-plugin:_")
    // scala
    implementation(libs.scalac.scoverage.plugin)
//    implementation(libs.scoverage.)
    // bloop
    implementation("ch.epfl.scala:gradle-bloop_2.13:_")
    // ch.epfl.scala:gradle-bloop_2.12:1.4.3

    // documentation / visualization
    // plant uml
    implementation("io.freefair.gradle:plantuml-plugin:_")
    implementation("gradle.plugin.org.dripto.gradle.plugin.plantuml:plugin:_")
    implementation("com.cosminpolifronie.gradle:gradle-plantuml-plugin:_")

    implementation("io.swagger.core.v3:swagger-gradle-plugin:_")
    implementation("gradle.plugin.ms.ralph.gradle:gradle-dependency-plantuml-exporter-plugin:_")
    implementation("io.spring.gradle:dependency-management-plugin:_")

    // kotlin
//    implementation(Kotlin.gradlePlugin)
    implementation("com.google.devtools.ksp:symbol-processing-gradle-plugin:_")
    // not finding jitpacked resource
//    implementation("com.strumenta.antlr-kotlin:antlr-kotlin-gradle-plugin:_")

    // quarkus related
    // quarkus incompatible with avrohugger (old scala 12.1) used by ddo-modeling.  Need to separate build.
//    implementation(libs.quarkus.gradle.plugin)
    implementation("org.kordamp.gradle:jandex-gradle-plugin:_")

    // Database
    implementation(CashApp.sqlDelight.gradlePlugin)

    // String utils
    // camel / snake etc
    // universal dependency for Gradle 5.3 and above
    // in case of multiplatform project, just specify the dependency for commonMain/commonTest source set
    implementation("net.pearx.kasechange:kasechange:_")
    // to here
    //    implementation("com.diffplug.spotless-changelog:spotless-changelog-plugin-gradle:_")
//    implementation(Kotlin.gradlePlugin)
    // implementation("com.palantir.baseline:com.palantir.gradle-baseline-config:_")
    // classpath for  id("com.palantir.baseline")
    //  id("app.cash.sqldelight") version "2.0.0-alpha05"
//    implementation("com.palantir.baseline:com.palantir.baseline-reproducibility:_")
//    implementation("com.palantir.baseline:com.palantir.baseline-config:_")
//    implementation("com.palantir.baseline:com.palantir.baseline-exact-dependencies:_")
//    implementation("com.palantir.baseline:gradle-baseline-java:_")
//    implementation(Kotlin.gradlePlugin)
    // im
//
// implementation("app.cash.sqldelight:runtime-jvm:_")
//    implementation("ru.vyarus:gradle-mkdocs-plugin:_")
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(
            JavaLanguageVersion.of(
                defaultJavaToolChainVersion ?: "21",
            ),
        ) // "8"
        // GraalvmToolchain support doesn't work for CI systems. Circle CI requires custom image since deprecating cicrleci/graal in favor of cimg/openjdk
        // (this as JavaToolchainSpec).vendor.set(JvmVendorSpec.GRAAL_VM)
    }
}
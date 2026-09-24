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

// val kotlinVersion = project.property("kotlinVersion") as String
// val jandexPluginVersion = project.property("jandexPluginVersion") as String
val defaultJavaToolChainVersion = project.findProperty("defaultJavaToolChainVersion") as String?
// val kasechangeVersion = project.property("kasechangeVersion") as String

dependencies {
    // enables gradle catalog for included convention plugins
    // DO NOT REMOVE
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(plugin(libs.plugins.avrohugger))
    implementation(plugin(libs.plugins.quarkus))
    // implementation(plugin(libs.plugins.kotlin.gradle))
    implementation(plugin(libs.plugins.kotlin.allopen))
    implementation(plugin(libs.plugins.scalafix))
    implementation(plugin(libs.plugins.nullaway))
    implementation(plugin(libs.plugins.errorprone))

    implementation(plugin(libs.plugins.sonarqube))
    // tool languages
    // node
    implementation(plugin(libs.plugins.gradle.node))
// code quality
    implementation(plugin(libs.plugins.spotless))
    implementation(plugin(libs.plugins.versions.manes))
    implementation(plugin(libs.plugins.versions.catalog))
//    implementation(libs.refreshVersions.plugin)
    implementation(plugin(libs.plugins.dependency.updates))
    // doc generation (requires python)
//    implementation("com.palantir.baseline:gradle-baseline-java:_")

    // CI build support
    // TODO: Remove ci plugin and use manual script
    implementation(plugin(libs.plugins.ci.detect))

//    implementation("org.unbroken-dome.gradle-plugins:gradle-testsets-plugin:_")
    // scala
    implementation(plugin(libs.plugins.scoverage))
//    implementation(libs.plugins.scoverage)
    // bloop
    implementation(libs.gradle.bloop)
    // ch.epfl.scala:gradle-bloop_2.13:1.4.3

    // documentation / visualization
    // plant uml
    implementation(plugin(libs.plugins.freefair.plantuml))
    implementation(plugin(libs.plugins.plantuml.dripto))
    

    implementation(plugin(libs.plugins.swagger))    
    implementation(plugin(libs.plugins.spring.dependency.management))

    // kotlin
//    implementation(Kotlin.gradlePlugin)
    implementation(plugin(libs.plugins.symbol.processing))
    // not finding jitpacked resource
//    implementation("com.strumenta.antlr-kotlin:antlr-kotlin-gradle-plugin:_")

    // quarkus related
    // quarkus incompatible with avrohugger (old scala 12.1) used by ddo-modeling.  Need a separate build.
    // TODO: check new avrohugger for quarkus compatibility

    implementation(plugin(libs.plugins.jandex.gradle))

    // Database
    implementation(plugin(libs.plugins.cashapp.sqldelight))

    // String utils
    // camel / snake etc
    // universal dependency for Gradle 5.3 and above
    // in case of multiplatform project, just specify the dependency for commonMain/commonTest source set
    implementation(libs.kasechange)
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

// Helper function that transforms a Gradle Plugin alias from a
// Version Catalog into a valid dependency notation for buildSrc
fun DependencyHandlerScope.plugin(plugin: Provider<PluginDependency>) =
    plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }

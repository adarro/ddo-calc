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
/*
 * Include (apply from) to configure common dependencies and source set configurations for Scala projects
 */
// import org.unbrokendome.gradle.plugins.testsets.dsl.testSets

pluginManager.withPlugin("scala-profiles") {
    val implementation by configurations.getting
    val testImplementation by configurations.getting
    val testRuntimeOnly by configurations.getting
    val scalaLibraryVersion: String by project
    val scalaMajorVersion: String by project
    dependencies {
        implementation(platform(project(":ddo-platform-scala")))
        implementation(libs.scala2.library)
        implementation(libs.enumeratum.s213)
        implementation(libs.typesafe.config)
        implementation(libs.kxbmap.configs.s213)
        // validation and rules
        implementation(libs.accord.core.s213)
        implementation(libs.logback.classic)
        implementation(libs.typesafe.scala.logging.s213)
        testImplementation(libs.scalatest.s213)
        // Needed for scalatest html reports (formerly depended on pegdown)
        testRuntimeOnly(libs.flexmark.all)
        testImplementation(libs.mockito.core)
        testImplementation(libs.scalacheck.s213)
        // JUnit 5
        testRuntimeOnly(libs.junit.platform.engine)
        testRuntimeOnly(libs.junit.platform.launcher)
        testRuntimeOnly(libs.scalatest.junit.runner)
        //  api(platform(":ddo-platform-scala"))
    }
}

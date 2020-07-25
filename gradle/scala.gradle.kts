/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
        implementation("org.scala-lang:scala-library:$scalaLibraryVersion")
        implementation(group = "com.beachape", name = "enumeratum_${scalaMajorVersion}")
        implementation(group = "com.typesafe", name = "config")
        implementation(group = "com.github.kxbmap", name = "configs_${scalaMajorVersion}")
        // validation and rules
        implementation(group = "com.wix", name = "accord-core_${scalaMajorVersion}")
        implementation(group = "ch.qos.logback", name = "logback-classic")
        implementation(group = "com.typesafe.scala-logging", name = "scala-logging_${scalaMajorVersion}")
        testImplementation(group = "org.scalatest", name = "scalatest_$scalaMajorVersion")
        testImplementation(group = "org.mockito", name = "mockito-all")
        // JUnit 5
        testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-engine")
        testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-launcher")
        testRuntimeOnly(group = "co.helmethair", name = ":scalatest-junit-runner")
        //  api(platform(":ddo-platform-scala"))
    }
}

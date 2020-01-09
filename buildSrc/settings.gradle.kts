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

pluginManagement {
    val testSetsPluginVersion: String by settings
    val kordampGradlePluginVersion: String by settings
    val scalaTestPluginVersion:String by settings
    val semVerPluginVersion:String by settings
    val scoveragePluginVersion:String by settings
    plugins {
        id("org.unbroken-dome.test-sets") version testSetsPluginVersion // "2.1.1"
        id("org.kordamp.gradle.project") version kordampGradlePluginVersion
     //   id("com.github.maiflai.scalatest") version scalaTestPluginVersion // "0.25"
    }

    repositories {
     //   gradlePluginPortal()
        jcenter()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
}

//apply(plugin = "org.kordamp.gradle.settings")
//
//configure<org.kordamp.gradle.plugin.settings.ProjectsExtension> {
//    directories = listOf("docs", "subprojects")
//    enforceNamingConvention = false
//    layout = "two-level"
//}
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
rootProject.name = "ddo-poc-javafx"

// Include core ddo projects

// includeBuild("../..")

pluginManagement {
    plugins {
        //id ("org.javafxports.jfxmobile") version "2.0.30"
        id("org.openjfx.javafxplugin") version "0.0.9" // Gluons' javafx plugin
//          id ("com.moowork.node") version "1.2.0"
//        id("org.siouan.frontend-jdk8") version "4.0.0"
        id("com.gradle.enterprise").version("3.4.1")
    }
    repositories {
        gradlePluginPortal()
        jcenter()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
}




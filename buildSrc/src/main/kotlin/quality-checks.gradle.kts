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
import org.gradle.kotlin.dsl.pmd


// import org.kordamp.gradle.plugin.base.

// Quality Checks Profile
plugins {
    pmd
    // Check the latest version at https://plugins.gradle.org/plugin/org.ec4j.editorconfig
    id("org.ec4j.editorconfig")
}



//dependencies {
//    // actually depend on the plugin to make it available:
//    (plugin("com.diffplug.gradle.spotless", version = "3.25.0"))
//}

// just a helper to get a syntax similar to the plugins {} block:
fun plugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"

configure<PmdExtension> {
    this.incrementalAnalysis.set(true)
    this.isConsoleOutput = true
}

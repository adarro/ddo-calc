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
val editorConfigVersion: String by project

val p = "printing $editorConfigVersion"
plugins {
    `kotlin-dsl`
    id("org.unbroken-dome.test-sets") // version "2.1.1"
    id("org.kordamp.gradle.project") apply (false)
    // TODO: use explicit receiver for variable to avoid hard-code
    //  id("org.ec4j.editorconfig") // version "0.0.3"
//    id("org.jmailen.kotlinter") version "2.2.0"
    // id("org.gradle.kotlin-dsl.ktlint-convention") version "0.4.1"
}

repositories {
    gradlePluginPortal()
    jcenter()
    mavenCentral()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

project.description = "build source plugin and common configuration"

//kotlinter {
////    ignoreFailures = false
////    indentSize = 4
////    continuationIndentSize = 4
////    reporters = arrayOf("checkstyle", "plain")
//    experimentalRules = true
////    disabledRules = emptyArray<String>()
////    fileBatchSize = 30
//}

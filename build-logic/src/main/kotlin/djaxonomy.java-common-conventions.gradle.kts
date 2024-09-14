/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2022-2023 Andre White.
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
    java
    id("djaxonomy.common-conventions")
}

val defaultJavaToolChainVersion: String by project
val jacocoToolVersion: String by project
val javaToolchainVersion =
    provider {
        defaultJavaToolChainVersion.toInt()
    }

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaToolchainVersion.get()))
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

// jacoco {
//     toolVersion = jacocoToolVersion
// //    reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
// }
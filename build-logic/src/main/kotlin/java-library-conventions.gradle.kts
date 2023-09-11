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
    id("djaxonomy.java-common-conventions")
    `java-library`
}

val javaLanguageVersion: String? by project
val DEFAULT_JAVA_LANGUAGE_VERSION = 11

fun jslValOrDefault(jVal: String?): Int {
    return jVal?.toIntOrNull() ?: DEFAULT_JAVA_LANGUAGE_VERSION
}

// See https://gist.github.com/adarro/0411f34ae1f048726b28e9f33e5c0a97 for JPMS revisit
//
// java {
//    toolchain {
//        languageVersion.set(JavaLanguageVersion.of(jslValOrDefault(javaLanguageVersion)))
//    }
// }

tasks {
    named("jar", Jar::class) {
        // AffixSlot is being duplicated but unsure why
        duplicatesStrategy = DuplicatesStrategy.WARN
    }
    withType<JavaCompile>().configureEach {
        options.generatedSourceOutputDirectory.set(file("$projectDir/src/generated/java"))
        options.compilerArgs.plusAssign("-Asemver.project.dir=$projectDir")
        modularity.inferModulePath.set(false)
        // sourceCompatibility = JavaVersion.VERSION_11.toString()
        //  targetCompatibility = JavaVersion.VERSION_11.toString()
        //   dependsOn("syncVersionFiles")
    }
}
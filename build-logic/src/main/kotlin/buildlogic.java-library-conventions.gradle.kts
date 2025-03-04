import org.gradle.accessors.dm.LibrariesForLibs

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
    id("buildlogic.java-common-conventions")
    `java-library`
    id("net.ltgt.nullaway")
    id("net.ltgt.errorprone")

//    alias(libs.plugins.errorprone)
}
val libs = the<LibrariesForLibs>()
// Toolchain moved to djaxonomy.common-conventions

dependencies {
    // library should use api vs implementation in java library
    api(libs.jspecify)
}

tasks {
    named("jar", Jar::class) {
        // AffixSlot is being duplicated but unsure why
        duplicatesStrategy = DuplicatesStrategy.WARN
    }

    withType<JavaCompile>().configureEach {
        options.generatedSourceOutputDirectory.set(file("$projectDir/src/generated/java"))
        options.compilerArgs.plusAssign("-Asemver.project.dir=$projectDir")
        modularity.inferModulePath.set(false)
    }
}
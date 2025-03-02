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
import org.gradle.accessors.dm.LibrariesForLibs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("jvm")
    id("buildlogic.java-common-conventions")
}

val libs = the<LibrariesForLibs>()

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {

        freeCompilerArgs.add(
            "-opt-in=kotlin.ExperimentalStdlibApi",
        )
    }
}

val defaultJavaToolChainVersion: String by project

dependencies {
    constraints {
        // Define dependency versions as constraints
        // implementation("org.apache.commons:commons-text:1.9")
        implementation(libs.github.oshai.kotlin.logging)
    }

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    // configuration objects https://github.com/sksamuel/hoplite
    implementation(libs.bundles.hoplite)
}

kotlin {
    jvmToolchain {
//        check(this is JavaToolchainSpec)
        languageVersion.set(JavaLanguageVersion.of(defaultJavaToolChainVersion))
    }
}
import io.truthencode.buildlogic.FALLBACK_JDK_VERSION
import io.truthencode.buildlogic.JLineClassifierFixAction
import net.ltgt.gradle.errorprone.errorprone
import net.ltgt.gradle.nullaway.nullaway
import org.gradle.accessors.dm.LibrariesForLibs

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
/**
 * Common java conventions
 * @author Andre White
 * @since 0.0.1
 * Includes general configurations for jvm projects that use the java plugin.
 * This includes kotlin / scala plugins which automatically apply the java plugin
 */
val libs = the<LibrariesForLibs>()
plugins {
    java
    id("buildlogic.common-conventions")
    id("net.ltgt.nullaway")
    id("net.ltgt.errorprone")
}

dependencies {
    errorprone(libs.nullaway.errorprone)
    errorprone(libs.errorprone.processor)
// add implementation dependency on jspecify for non-java-library projects
// java-library will have an API dependency
    if (!plugins.hasPlugin("java-library")) {
        implementation(libs.jspecify)
    }
}

// See https://gist.github.com/adarro/0411f34ae1f048726b28e9f33e5c0a97 for JPMS revisit
// TODO: remove hard-coded JDK 21 move to lib constant as least worst case?
// TODO: locate all instances using this concept and consolidate
val defaultJavaToolChainVersion = providers.gradleProperty("defaultJavaToolChainVersion")

val javaToolchainVersion =
    provider {
        libs.versions.java.version
            .orElse(defaultJavaToolChainVersion)
            .getOrElse(FALLBACK_JDK_VERSION)
            .toInt()
    }

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaToolchainVersion.get()))
    }
}

afterEvaluate {

    tasks
        .withType<JavaCompile>()
        .configureEach {

            logger.debug("$name: Configuring Compiler (Java Common)")
            logger.debug("Checking for quarkus profile presence")
            val qProfile = providers.gradleProperty("quarkus.profile")
            logger.debug("quarkus profile presence: ${qProfile.isPresent}")
            if (qProfile.isPresent) {
                logger.debug("current profile: ${qProfile.get()}")
                // add xLint, deprecaction, unchecked, and nullaway args to dev / test profiles and lean optimizations for production
//        options.compilerArgs.add("-Aquarkus.profile=${qProfile.get()}")
            }
            options.encoding = "UTF-8"
            options.release =
                libs.versions.java.version
                    .get()
                    .toInt()
            options.encoding = "UTF-8"
            options.errorprone {
                val regExcludeScala = Regex("""(.*\.scala|.*/generated*/.*)""")
                excludedPaths = regExcludeScala.pattern
                disableWarningsInGeneratedCode = true

                nullaway {
                    suggestSuppressions = true
                    onlyNullMarked = true
                    isAssertsEnabled = true
                    isJSpecifyMode = true
                }
            }

            // Add Type Annotations to Symbol for JDK 21+ to support NullAway and other tools that rely on type annotations.

            // We use a lazy provider to safely inspect the toolchain metadata before execution
            val metadata = javaCompiler.map { it.metadata }.get()

            val vendorName = metadata.vendor.lowercase()
            val version = metadata.languageVersion.asInt()
            logger.debug("checking vend $vendorName jdk $version")
//    // 1. Check version: Must be less than JDK 22
//    // 2. Check vendor: Exclude Oracle, ensure it is an OpenJDK-based build
            val isTargetVersion = version < 22
            val isNotOracle = !vendorName.contains("oracle")
            // TODO: refactor to a list of vendors
            val isOpenJdk =
                vendorName.contains("openjdk") ||
                    vendorName.contains("adoptium") ||
                    vendorName.contains("temurin") ||
                    vendorName.contains("zulu") ||
                    vendorName.contains("azul") ||
                    vendorName.contains("corretto") ||
                    vendorName.contains("graalvm") && vendorName.contains("community")

            if (isTargetVersion && isNotOracle && isOpenJdk) {
                logger.debug("adding type annotation -XDaddTypeAnnotationsToSymbol for $vendorName $version")
                options.compilerArgs.addAll(listOf("-XDaddTypeAnnotationsToSymbol=true"))
            } else if (isTargetVersion) {
                logger.warn("Not adding type annotation option due to non-openjdk vendor: $vendorName $version")
            } else {
                logger.debug("Not adding type annotation option due to target version greater than 21: $vendorName $version")
            }
            // FIXME: do not include for release
            options.compilerArgs.addAll(listOf("-Xlint:unchecked", "-Xlint:deprecation"))
        }
} // afterEvaluate

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

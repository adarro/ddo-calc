import gradle.kotlin.dsl.accessors._fef3d86dac4f68a9dcfb4a9e845afa4b.errorprone
import gradle.kotlin.dsl.accessors._fef3d86dac4f68a9dcfb4a9e845afa4b.nullaway
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

val defaultJavaToolChainVersion: String by project

val javaToolchainVersion =
    provider {
        defaultJavaToolChainVersion.toInt()
    }

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaToolchainVersion.get()))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.errorprone {
//        check("NullAway", CheckSeverity.ERROR)
        val regExcludeScala = Regex("""(.*\.scala|.*/generated*/.*)""")
        logger.error("regExcludeScala: $regExcludeScala")
        excludedPaths = regExcludeScala.pattern
        disableWarningsInGeneratedCode = true

        nullaway {
            suggestSuppressions = true
//        autoFixSuppressionComment = "Suggested by (ErrorProne) NullAway"
            onlyNullMarked = true
            isAssertsEnabled = true
            isJSpecifyMode = true
        }
    }
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}
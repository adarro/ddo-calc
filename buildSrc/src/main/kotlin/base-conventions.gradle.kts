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
    id("com.diffplug.spotless")
}

enum class ScriptLanguage { GradleBuild, KotlinScriptBuild }

fun buildLang(): ScriptLanguage {
    return if (project.buildFile.name.endsWith("kts")) ScriptLanguage.KotlinScriptBuild else ScriptLanguage.KotlinScriptBuild
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    if (buildLang() == ScriptLanguage.KotlinScriptBuild) {
        logger.quiet("SPOTLESS: applying / configuring kotlin script formatting to ${project.name}")
        kotlinGradle {
            diktat("1.0.0-rc.4")
        }
    }
    project.plugins.withId("scala") {
        logger.quiet("SPOTLESS: applying / configuring scala formatting to ${project.name}")
        scala {
            val scalaFmtVersion: String by project
            // version and configFile are both optional
            scalafmt(scalaFmtVersion).configFile(rootProject.file(".scalafmt.conf"))
        }
    }
}


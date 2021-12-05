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
/**
 * Provides UML diagraming and documentation
 */
plugins {
    id("com.cosminpolifronie.gradle.plantuml")
    id("org.dripto.gradle.plugin.plantuml.plantuml-generator")
}

tasks.withType(org.dripto.gradle.plugin.plantuml.task.PlantUmlGeneratorTask::class.java) {
    destination = "${buildDir}/diagrams/class-diagram.puml"
    config {
        scanPackages = listOf("io.truthencode.ddo")
    }
    asciiConfig {
        enableAsciidocWrapper = true
        asciidocDiagramImageFormat = "png"
        asciidocDiagramName = "ascii_diagram"
        asciidocDiagramBlockDelimiter = "----"
    }
}

plantUml {
    render(
        mapOf(
            "input" to "${buildDir}/diagrams/*.puml",
            "output" to "${project.buildDir.absolutePath}/reports/diagrams",
            "format" to "png",
            "withMetadata" to true
        )
    )
}

listOf(
    com.cosminpolifronie.gradle.plantuml.tasks.PlantUmlIOTask::class.java,
    com.cosminpolifronie.gradle.plantuml.tasks.PlantUmlOutputForInputTask::class.java,
    com.cosminpolifronie.gradle.plantuml.tasks.PlantUmlTask::class.java
).forEach { t ->
    tasks.withType(t) {
        dependsOn("generateUml")

    }
}

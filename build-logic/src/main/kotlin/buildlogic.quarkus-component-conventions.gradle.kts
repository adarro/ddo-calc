import org.kordamp.gradle.plugin.jandex.tasks.JandexTask

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
    id("org.kordamp.gradle.jandex")
}

/*
APPLIES TO:

This convention should be applied to any project that
- Does NOT directly create a quarkus instance
- uses quarkus components
- is to be 'discovered' / injected via a supported CDI or other annotation Quarkus needs to
be aware of at compile time.

DOES NOT APPLY TO:
(Mutually exclusive) actual quarkus application

You should instead apply 'buildlogic.quarkus-application-conventions' if the module actually invokes
the quarkus engine directly.

WHAT THIS DOES:

Defines Quarkus version information (Bom)

Adds Jandex plugin to provide index of classes / objects / beans Main Quarkus app needs to register.
 */
tasks {
    withType(JavaCompile::class.java) {
        // Add index to test compile dependency explicitly
        if (name.contains("Test")) {
            val tsk = tasks.withType(JandexTask::class.java)
            dependsOn(tsk)
        }
        withType<ScalaDoc> {
            dependsOn(tasks.named("jandex"))
        }
    }
}
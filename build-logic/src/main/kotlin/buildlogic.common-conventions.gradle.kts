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
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("code-quality")
    id("com.github.ben-manes.versions")
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    maven {
        url = uri("https://repo.orbitalhq.com/release")
    }
    maven { url = uri("https://jitpack.io") }
}

// updated extension function (requires kotlin 2.0?)
// fun String.isNonStable(): Boolean {
//  val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { uppercase().contains(it) }
//  val regex = "^[0-9,.v-]+(-r|-jre|-android)?$".toRegex()
//  val isStable = stableKeyword || regex.matches(this)
//  return isStable.not()
// }
//
// tasks.named<DependencyUpdatesTask>("dependencyUpdates") {
//  checkConstraints = true
//  rejectVersionIf {
//    (candidate.version.isNonStable() && !currentVersion.isNonStable())
//     || !satisfiesDeclaredBound // need to see where this is used and if it is needed
//  }
//
// }

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

// https://github.com/ben-manes/gradle-versions-plugin
tasks.withType<DependencyUpdatesTask> {
    this.gradleReleaseChannel = "current"
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

tasks.register("listDependencyCapabilities") {
    group = "help"
    description = "Lists all resolved dependencies and their associated capabilities."

    // Ensure we run after configurations are ready
    doLast {
        // Target a specific configuration (e.g., runtimeClasspath or compileClasspath)
        val cfg = configurations.named { n -> n.equals("runtimeClasspath") }.firstOrNull() // .get()

        // Retrieve the root of the resolved dependency graph
        val rootComponent: ResolvedComponentResult? = cfg?.incoming?.resolutionResult?.root

        println("\n=== Resolved Dependencies & Their Capabilities ===")

        // Loop through all resolved components in the graph
        rootComponent?.dependencies?.forEach { dependencyResult ->
            if (dependencyResult is org.gradle.api.artifacts.result.ResolvedDependencyResult) {
                val selected = dependencyResult.selected
                val id = selected.id

                println("\nDependency: $id")

                // Inspect all variants resolved for this component
                selected.variants.forEach { variant ->
                    println("  Variant: ${variant.displayName}")

                    val capabilities = variant.capabilities
                    if (capabilities.isEmpty()) {
                        // Every dependency has an implicit default capability matching its GAV coordinates
                        println("    Capabilities: [Implicit default capability]")
                    } else {
                        println("    Capabilities:")
                        capabilities.forEach { capability: Capability ->
                            println("      - ${capability.group}:${capability.name}:${capability.version}")
                        }
                    }
                }
            }
        }
    }
}
//
// if (project == project.rootProject) {
//    apply(plugin = "nl.littlerobots.version-catalog-update")
//    versionCatalogUpdate {
//        // sort the catalog by key (default is true)
//        sortByKey = true
//        // Referenced that are pinned are not automatically updated.
//        // They are also not automatically kept however (use keep for that).
//        pin {
//            // pins all libraries and plugins using the given versions
//            versions = listOf("scala3-version", "scala2-version")
//
//            // pins specific libraries that are in the version catalog
//            // libraries
//
//            // pins specific plugins that are in the version catalog
//            // plugins
//
//            // pins all libraries (not plugins) for the given groups
//            // groups
//        }
//        keep {
//            // keep has the same options as pin to keep specific entries
//            // note that for versions it will ONLY keep the specified version, not all
//            // entries that reference it.
// //        versions = ["my-version-name", "other-version"]
// //        libraries = [libs.my.library.reference, libs.my.other.library.reference]
// //        plugins = [libs.plugins.my.plugin, libs.plugins.my.other.plugin]
// //        groups = ["com.somegroup", "com.someothergroup"]
//
//            // keep versions without any library or plugin reference
//            keepUnusedVersions = true
//            // keep all libraries that aren't used in the project
//            keepUnusedLibraries = true
//            // keep all plugins that aren't used in the project
//            keepUnusedPlugins = true
//        }
//    }
// }

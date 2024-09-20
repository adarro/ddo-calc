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
import com.mooltiverse.oss.nyx.state.State
import com.vanniktech.dependency.graph.generator.DependencyGraphGeneratorExtension
import guru.nidi.graphviz.attribute.Color
import guru.nidi.graphviz.attribute.Style
import ru.vyarus.gradle.plugin.python.PythonExtension
import ru.vyarus.gradle.plugin.python.task.PythonTask
import java.lang.management.ManagementFactory
import java.text.SimpleDateFormat
import java.util.*

plugins {
    id("org.scoverage") apply (false)
    // may need node support
//    id("node-conventions")

    idea
    `maven-publish`
    id("com.dorongold.task-tree") version "2.1.0" // Temp until working solution to userhome version script
    id("com.github.ManifestClasspath") version "0.1.0-RELEASE"
    `build-dashboard`
    // Move this to convention plugin for easier portability
    id("com.github.ben-manes.versions") version "0.41.0"
    id("nl.littlerobots.version-catalog-update") version "0.8.1"
    id("ru.vyarus.mkdocs")
    id("org.sonarqube")
    id("com.vanniktech.dependency.graph.generator") version "0.8.0"
}

// diag borrowed from thing guy who is also tracking down mem issue.
// https://github.com/TWiStErRob/net.twisterrob.gradle/blob/4a0afe2522db725ff217183fa2b73916d3460397/test/src/main/resources/net/twisterrob/gradle/test/runtime.init.gradle.kts
// Migrate to https://docs.gradle.org/current/userguide/dataflow_actions.html at some point if needed
// TODO deprecated without replacement https://github.com/gradle/gradle/issues/20151
// Best effort for now as it won't work with configuration cache.
gradle.buildFinished { println(memoryDiagnostics()) }

fun memoryDiagnostics(): String {
    fun format(
        max: Long?,
        used: Long,
    ): String {
        fun mb(bytes: Long): String = "${bytes / 1024 / 1024}MB"
        return if (max == null) {
            "${mb(used)} (unlimited)"
        } else {
            "${mb(used)}/${mb(max)} (${mb(max - used)} free)"
        }
    }

    val heap = Runtime.getRuntime()
    heap.gc() // Best effort to get more accurate numbers.
    val heapMax = heap.maxMemory().takeIf { it != Long.MAX_VALUE }
    val heapUsed = heap.totalMemory() - heap.freeMemory()
    val meta = ManagementFactory.getMemoryPoolMXBeans().single { it.name == "Metaspace" }
    val metaMax = meta.usage.max.takeIf { it != -1L }
    val metaUsed = meta.usage.used
    return "Gradle memory: heap = ${format(heapMax, heapUsed)}, metaspace = ${format(metaMax, metaUsed)}."
}

// apply(plugin = "org.scoverage")
// general project information
val projectName = project.name
val gitHubAccountName = "truthencode"
val gitHubBaseSite = "https://github.com/$gitHubAccountName/${project.name}"
val siteIssueTracker = "$gitHubBaseSite/issues"
val gitExtension = "${project.name}.git"
val siteScm = "$gitHubBaseSite/$gitExtension"

// Used for versioned documentation
val mkDocsLatestAlias: String? by project

fun mkDocAlias(): String = mkDocsLatestAlias ?: "Latest"
group = "io.truthencode"

// MkDocs config
mkdocs {
    sourcesDir = "."
    strict = false
    with(publish) {
        this.generateVersionsFile = true
    }
    python.scope = PythonExtension.Scope.VIRTUALENV_OR_USER
}

/* TODO: Documentation Task
Include Acceptance tests and possibly coverage in mkdocs

depend on testAggregateTestReport (needs check) and aggregateScoverage
scoverage aggregates to ./build/reports/scoverage/
testAggregateTestReport aggregates to ./subprojects/common/ddo-test-results/build/reports/tests/[test-type]
 */

val devRequirementsIn =
    listOf(
        "pip-tools:7.3.0",
    )

val requirementsIn =
    listOf(
        "mkdocs:1.5.2",
        "mkdocs-material:9.2.8", // trans req: regex which may require cc (gcc et all)
        "mkdocs-monorepo-plugin:1.0.5",
        "mkdocs-markdownextradata-plugin:0.2.5",
        "mkdocs-graphviz:1.5.3",
        "mike:1.1.2",
        "plantuml-markdown:3.9.2",
        "mkdocs-embed-file-plugins:2.0.6",
        "mkdocs-callouts:1.9.0",
        "mkdocs-awesome-pages-plugin:2.9.2",
        "mkdocs-include-markdown-plugin:6.0.1",
    )

tasks.register("generateRequirementsIn") {
    description = "generates a requirements.in dependency file for Python / MkDocs"
    group = "documentation"
    val rIn = layout.projectDirectory.file("requirements.in")
    this.outputs.files(rIn)
    val rTxt = requirementsIn.joinToString("\n") { it.replace(":", "==") }
    logger.error("rText : \n$rTxt")
    rIn.asFile.writeText(rTxt)
}

python {
    this.pip(
        requirementsIn.plus(devRequirementsIn),
    )
//    installVirtualenv = true
}

tasks.register("dumpSomeDiagnostics") {
    group = "utility"
    description = "prints nyx plugin state information"
    dependsOn(project.tasks.named("nyxInfer"))
    doLast {
        if (project.hasProperty("nyxState")) {
            val nyxState: State = project.findProperty("nyxState") as State
            println(nyxState.bump)
            println(nyxState.directory.absolutePath)
            println(nyxState.scheme.toString())
            println(SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.US).format(Date(nyxState.timestamp)))
            println(nyxState.version)
        }
    }
}

// Currently need to manually verify this matches mkdocs.yml value
tasks.register("syncDocVersion", PythonTask::class) {
    group = "documentation"
    description = "Syncronizes nyx Semver with Readthedocs"
    dependsOn(project.tasks.named("nyxInfer"))
    val nyxState: State = project.findProperty("nyxState") as State
    module = "mike"
    command = "deploy --push --update-aliases ${nyxState.version}${mkDocAlias()}\n"
    doLast {
        if (project.hasProperty("nyxState")) {

            run()
        }
    }
}

tasks.register("syncRequirements", PythonTask::class) {
    group = "documentation"
    description = "synchronizes Python setup tools dependency files with Gradle's"
    dependsOn(tasks.named("nyxInfer"), tasks.named("generateRequirementsIn"))
    module = "piptools"
    command = "compile"
    doLast {
        run()
    }
}

/*

Reporting tasks
// coverage
reportScoverage


// aggregate
aggregateScoverage
testAggregateTestReport
buildDashboard

 */

tasks.register("showMyVersion") {
    group = "utility"
    description = "displays project version"
    val v = project.version
    logger.info("project version: $v")
    logger.info(project.gradle.gradleVersion)
}

class VersionInfo {
    /**
     * Version properties
     * Reads the version.properties file
     * @return property bag of version information
     */
    private fun versionProperties(): Properties {
        val prop = Properties()
        val versionFile = rootProject.file("version.properties")
        if (!versionFile.exists()) {
            logger.warn("version.properties file does not exist!")
        } else {
            java.io.FileInputStream(versionFile).use { prop.load(it) }
        }
        return prop
    }

    private fun optionalProperty(
        key: String,
        defaultValue: String? = null,
    ): String? = readOptionalProperty(props, key, defaultValue)

    val props = versionProperties()
    val major = optionalProperty("version.major", "0")?.toInt()
    val minor = optionalProperty("version.minor", "0")?.toInt()

    // Default patch to 0 unless major and minor are 0, as 0.0.0 is a silly version.
    val patchValue: Int? =
        if ((major ?: 0) + (minor ?: 0) == 0) 1 else optionalProperty("patch", "0")?.toInt()

    val patch = patchValue ?: 0
    val buildMetaValue = optionalProperty("version.buildMeta")
    val prereleaseValue = optionalProperty("version.prerelease")
    val separator = optionalProperty("version.separator", ".")
    val prereleasePrefix = optionalProperty("version.prerelease.prefix")
    val buildMetaPrefix = optionalProperty("version.buildMeta.prefix")
    val prerelease = if (!prereleaseValue.isNullOrEmpty()) "$prereleasePrefix$prereleaseValue" else ""
    val buildMeta = if (!buildMetaValue.isNullOrEmpty()) "$buildMetaPrefix$prereleaseValue" else ""
    val version = "$major$separator$minor$separator$patch$prerelease$buildMeta"

    companion object {
        fun readOptionalProperty(
            prop: Properties,
            key: String,
            defaultValue: String? = null,
        ): String? {
            val oKey =
                if (prop.containsKey(key)) {
                    prop.getProperty(key)
                } else {
                    null
                }
            val pKey =
                when {
                    oKey.isNullOrBlank() -> null
                    else -> prop.getProperty(key)
                }

            return pKey ?: defaultValue
        }
    }
}

//
// tasks.withType<com.hierynomus.gradle.license.tasks.LicenseCheck>() {
//    this.encoding = "UTF-8"
// }

val foo = project.rootProject.layout.files("version.properties")

val syncVersionFilesFromRoot by tasks.registering(Copy::class) {
    if (rootProject != project) {
        logger.warn("This task should only be run from the root project")
    } else {
        logger.warn("in root project, propagating properties")
        rootProject.allprojects.forEach {
            if (it != rootProject) {
                logger.warn("copy to -> ${it.name}")
                from(foo)
                into(it.layout.buildDirectory)
            }
        }
    }
}

allprojects {
    repositories {
        mavenCentral()
    }

    tasks.register("printConfigurations") {
        group = "utility"
        description = "prints existing configurations for a given project.  See alt resolvableConfigurations"
        doLast {
            println("Project Name: $project.name configurations:")
            configurations.forEach {
                println("\t$it.name")
            }
        }
    }
}

sonar {
    properties {
        property("sonar.projectKey", "truthencode_ddo-calc")
        property("sonar.organization", "truthencode")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

configure<DependencyGraphGeneratorExtension> {
    generators.create("jetbrainsLibraries") {
        include = { dependency -> dependency.moduleGroup.startsWith("org.jetbrains") } // Only want Jetbrains.
        children = { true } // Include transitive dependencies.
        dependencyNode = { node, dependency -> node.add(Style.FILLED, Color.rgb("#AF1DF5")) } // Give them some color.
    }
}
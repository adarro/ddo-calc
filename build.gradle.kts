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
import ru.vyarus.gradle.plugin.python.task.PythonTask
import java.text.SimpleDateFormat
import java.util.*

plugins {
//    id("code-quality")
    // id("org.kordamp.gradle.project")
    // may need node support
    id("node-conventions")

    idea
//    id("net.thauvin.erik.gradle.semver")
    `maven-publish`
    id("com.dorongold.task-tree") version "2.1.0" // Temp until working solution to userhome version script
    id("com.github.ManifestClasspath") version "0.1.0-RELEASE"
    `build-dashboard`
    // Move this to convention plugin for easier portability
    id("com.github.ben-manes.versions") version "0.41.0"
    id("nl.littlerobots.version-catalog-update") version "0.8.1"
    id("ru.vyarus.mkdocs")
//    id("ru.vyarus.mkdocs")  version "3.0.0" apply (false)
    //  id ("be.vbgn.ci-detect") version "0.1.0"
}


// general project information
val projectName = project.name
val gitHubAccountName = "truthencode"
val gitHubBaseSite = "https://github.com/$gitHubAccountName/${project.name}"
val siteIssueTracker = "${gitHubBaseSite}/issues"
val gitExtension = "${project.name}.git"
val siteScm = "${gitHubBaseSite}/$gitExtension"

// Used for versioned documentation
val mkDocsLatestAlias: String? by project
fun mkDocAlias(): String {
    return mkDocsLatestAlias ?: "Latest"
}
group = "io.truthencode"

// MkDocs config
mkdocs {
    sourcesDir = "."
    strict = false
    with(publish) {
        this.generateVersionsFile = true
    }


}

python {
    this.pip(
        "mkdocs-monorepo-plugin:1.0.5",
        "mkdocs-markdownextradata-plugin:0.2.5",
        "mike:1.1.2",
        "plantuml-markdown:3.9.2",
    )
    installVirtualenv = true

}

tasks.register("dumpSomeDiagnostics") {
    dependsOn(project.tasks.named("nyxInfer"))
    doLast {
        if (project.hasProperty("nyxState")) {
            val nyxState: State = project.findProperty("nyxState") as State
            println(nyxState.bump)
            println(nyxState.directory.absolutePath)
            println(nyxState.scheme.toString())
            println(SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.US).format(java.util.Date(nyxState.timestamp)))
            println(nyxState.version)
        }

    }
}

// Currently need to manually verify this matches mkdocs.yml value
tasks.register("syncDocVersion", PythonTask::class) {
    dependsOn(project.tasks.named("nyxInfer"))
    doLast {
        if (project.hasProperty("nyxState")) {
            val nyxState: State = project.findProperty("nyxState") as State
            val cmd = "mike deploy --push --update-aliases ${nyxState.version}${mkDocAlias()}\n"
        }
    }
}

// val releaseActive: Boolean? = rootProject.findProperty("release") as Boolean?

// config {
// //    release = if (releaseActive != null) releaseActive!! else false
//     info {
//         name = "DDO Calculations"
//         vendor = "TruthEncode"
//         description = "DDO Character Analyzer and Planner"
//         inceptionYear = "2015"
//         version = VersionInfo().version

//         links {
//             website = gitHubBaseSite
//             issueTracker = siteIssueTracker
//             scm = siteScm
//         }

//         scm {
//             url = gitHubBaseSite
//             developerConnection = "scm:git:git@github.com:$gitHubAccountName/${gitExtension}"
//             connection = "scm:git:git://github.com/github.com/$gitHubAccountName/$gitExtension"
//         }

//         organization {
//             name = "TruthEncode"
//             url = "https://github.com/truthencode"
//         }

//         people {
//             person {
//                 id = "adarro"
//                 name = "Andre White"
//                 roles = listOf("developer", "owner")
//             }
//         }

//         artifacts {
//             minpom {
//                 enabled = true
//             }
//             jar {
//                 enabled = false
//             }
//         }
//     }

//     licensing {
//         excludes = setOf(
//             "**/*.md",
//             "**/*.sql",
//             "**/avro/**/*.scala",
//             "buildSrc\\build\\kotlin-dsl\\plugins-blocks\\extracted\\*.kts",
//             "**/*.conf",
//         )
//         includes = setOf("src/main/java/**/*.java", "src/main/scala/*.scala", "src/main/kotlin/**/*.kt")
//         licenses {
//             license {
//                 id = "Apache-2.0" // org.kordamp.gradle.plugin.base.model.LicenseId.APACHE_2_0
//                 url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
//             }
//         }
//     }
// }

tasks.register("showMyVersion") {
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

    private fun optionalProperty(key: String, defaultValue: String? = null): String? {
        return readOptionalProperty(props, key, defaultValue)
    }


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
        fun readOptionalProperty(prop: Properties, key: String, defaultValue: String? = null): String? {
            val oKey = if (prop.containsKey(key)) {
                prop.getProperty(key)
            } else null
            val pKey = when {
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

fun bar() {
    val vp = project.rootProject.layout.files("version.properties").singleFile
    project.rootProject.allprojects.forEach {

    }
}

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

//    val syncVersionFiles by tasks.registering(Copy::class) {
//        if (rootProject != project) {
//            logger.warn("We are updating properties file in ${project.name}")
//            from(foo)
//            into(layout.projectDirectory)
//        } else {
//            logger.warn("in root project, nothing doing")
//        }
//    }
//    tasks.withType<ProcessResources> {
//        mustRunAfter(syncVersionFiles)
//    }
//    tasks.withType<com.diffplug.gradle.spotless.SpotlessTask> {
//        mustRunAfter(syncVersionFiles)
//    }
//    tasks.withType<com.hierynomus.gradle.license.tasks.LicenseCheck> {
//        mustRunAfter(syncVersionFiles)
//    }
}

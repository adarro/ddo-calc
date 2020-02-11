/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andre White.
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
// Welcome to a clean start
// import com.hierynomus.gradle.license.tasks.LicenseCheck


import org.gradle.plugins.ide.idea.model.Module
import org.gradle.plugins.ide.idea.model.ModuleDependency

plugins {
    // CI / CD
    id("org.shipkit.java") version "2.2.6"
    // Formatting, linting / code standards and conventions etc
    id("org.ec4j.editorconfig")
    //  id("org.gradle.kotlin-dsl.ktlint-convention") apply (false)
    id("quality-checks")
    id("com.github.maiflai.scalatest") apply (false)
    id("org.scoverage") apply (false)
    //  id("com.github.prokod.gradle-crossbuild") // apply (false)
    id("org.unbroken-dome.test-sets") apply (false)
    id("org.kordamp.gradle.project") // apply (false)
    idea



}

repositories {
    jcenter()
    mavenCentral()
}



// general project information
val projectName = project.name
val gitHubAccountName = "truthencode"
val gitHubBaseSite = "https://github.com/$gitHubAccountName/${project.name}"
val siteIssueTracker = "${gitHubBaseSite}/issues"
val gitExtension = "${project.name}.git"
val siteScm = "${gitHubBaseSite}/$gitExtension"

val releaseActive: Boolean? = rootProject.findProperty("release") as Boolean?


config {
    release = if (releaseActive != null) releaseActive!! else false

    info {
        name = "DDO Calculations"
        vendor = "TruthEncode"
        description = "DDO Character Analyzer and Planner"
        inceptionYear = "2015"

        links {
            website = gitHubBaseSite
            issueTracker = siteIssueTracker
            scm = siteScm
        }

        scm {
            url = gitHubBaseSite
            developerConnection = "scm:git:git@github.com:$gitHubAccountName/${gitExtension}"
            connection = "scm:git:git://github.com/github.com/$gitHubAccountName/$gitExtension"
        }

        people {
            person {
                id = "adarro"
                name = "Andre White"
                roles = listOf("developer", "owner")
            }
        }
    }

    licensing {
        licenses {
            license {
                id = "Apache-2.0" //org.kordamp.gradle.plugin.base.model.LicenseId.APACHE_2_0
            }
        }
    }
}

license {
    header = rootProject.file("buildResources/LICENSE_HEADER")
    ignoreFailures = true
    strictCheck = true
    excludes(listOf("buildsrc\\build\\kotlin-dsl\\plugins-blocks\\extracted\\**"))

}
idea {
    project {
        vcs = "Git"
        ipr {
            beforeMerged(Action<org.gradle.plugins.ide.idea.model.Project> {
                modulePaths.clear()
            })
        }

        module {
            iml {
                whenMerged(Action<Module> {
                    dependencies.forEach {
                        (it as ModuleDependency).isExported = true
                    }
                })
            }
        }
    }

    module.iml {
        beforeMerged(Action<Module> {
            dependencies.clear()
        })
    }
}

//        configure<org.gradle.plugins.ide.idea.model.IdeaModel> {
//
//        }
//crossBuild {
//
//}

allprojects {
    apply {
        // plugin("org.ec4j.editorconfig")
        plugin("quality-checks")

    }
    // scala cross building
//    pluginManager.withPlugin("com.github.prokod.gradle-crossbuild") {

//        crossBuild {
//
//            scalaVersionsCatalog = ['2.11':'2.11.12', '2.12':'2.12.8']
//
//            builds {
//                spark240_211
//                spark243_212
//            }
//        }
//    }
    repositories {
        jcenter()
        mavenCentral()
    }
}

//subprojects {
//    // filter out platform projects (i.e. java-platform plugin applied not java-library
//    this.pluginManager.withPlugin("java-library") {
//        project.apply {
//            plugin("org.unbroken-dome.test-sets")
//        }
//    }
//
//}

// tasks.getByPath("check").dependsOn("licenseFormat")
editorconfig {
    excludes = listOf("**/*.gradle.kts", "gradle.properties", "**/gradlew.bat", "gradle", "gradlew")
}

// shipkit config with gradle 6
tasks.withType(org.shipkit.gradle.notes.AbstractReleaseNotesTask::class.java) {
    this.publicationRepository = "https://dl.bintray.com/truthencode/ddo-service"
}

fun String.firstPart(): String {
    return this.split(".").first()
}

tasks.create("complianceCheck") {
    this.description = "Formats code according to editorConfig and applies any missing license headers"
    dependsOn("editorconfigCheck", "license")
    group = "compliance"
}

tasks.create("complianceFormat") {
    this.description = "Formats code according to editorConfig and applies any missing license headers"
    dependsOn("editorconfigFormat", "licenseFormat")
    group = "compliance"
}

tasks.create("showSubDir") {
    // TODO: Remove this task before commit
    val directory = java.nio.file.Paths.get("${rootDir.path}/subprojects")
    java.nio.file.Files.find(
        directory,
        Integer.MAX_VALUE,
        { _: java.nio.file.Path, attributes: java.nio.file.attribute.BasicFileAttributes ->
            attributes.isDirectory
        }).use { dir ->
        dir.forEach { d ->
            val files =
                d.toFile().listFiles { p, s -> s.matches(Regex(".*build\\.gradle(\\.kts)?")) } //({{f,s -> true}})

            if (files?.isNullOrEmpty() != true) {
                if (files.size != 1)
                    logger.warn("Multiple build files located in project directory $d")
                val projectDir = java.nio.file.Paths.get(".") //. d.relativize(directory)
                val first = files.first()
                val projectName = first.name.firstPart()
                logger.info("Including Project $projectName \t projectDir: $projectDir \t BuildFile: $first")
            }
        }
    }


}
// rootProject.apply { from(rootProject.file("gradle/shipkit")) }
/* rootProject.apply {
    afterEvaluate {
        apply{from(rootProject.file("gradle/ide.gradle.kts"))}
    }

} */

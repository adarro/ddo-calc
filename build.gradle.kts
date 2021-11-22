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
    id ("com.github.hierynomus.license") version "0.16.1"
    id("org.kordamp.gradle.project")
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

        artifacts {
            minpom {
                enabled = false
            }
        }
    }

    licensing {
        licenses {
            license {
                id = "Apache-2.0" //org.kordamp.gradle.plugin.base.model.LicenseId.APACHE_2_0
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"

            }
        }
    }
}



//tasks.withType<com.hierynomus.gradle.license.tasks.LicenseFormat>() {
//    header = rootProject.file("gradle/LICENSE_HEADER")
//    ignoreFailures = true
//    strictCheck = true
//    URI = "https://www.apache.org/licenses/LICENSE-2.0.txt"
//// extra.set("uri","https://www.apache.org/licenses/LICENSE-2.0.txt")
//
//  //  excludes(listOf("buildsrc\\build\\kotlin-dsl\\plugins-blocks\\extracted\\**"))
//}
//
//tasks.withType<com.hierynomus.gradle.license.tasks.LicenseCheck>() {
//    this.encoding = "UTF-8"
//}

allprojects{
    repositories{
        mavenCentral()
    }
}
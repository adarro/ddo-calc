/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
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
    `kotlin-dsl`
    id("org.unbroken-dome.test-sets") // version "2.1.1"
    // id("org.kordamp.gradle.project") // apply(false)
}

repositories {
    gradlePluginPortal()
    jcenter()
    mavenCentral()

}

//kotlinDslPluginOptions {
//    experimentalWarning.set(false)
//}


//configure<LicenseExtension> {
//    excludePatterns = listOf("scala-profiles.gradle.kts","**/*.gradle.kts")
//}

project.description = "build source plugin and common configuration"

//configure<ProjectConfigurationExtension> {
//    info {
//        name = "Sample"
//        vendor = "Acme"
//        description = "Sample project"
//
//        links {
//            website = "https://github.com/joecool/sample"
//            issueTracker = "https://github.com/joecool/sample/issues"
//            scm = "https://github.com/joecool/sample.git"
//        }
//    }
//
//    licensing {
//        // Disabled within BuildSrc because kotlin-dsl plugin strips header when extracting kscripts
//        enabled = false
////        licenses {
////            license {
////
////            }
////        }
//        licenses {
//            license {
//                id = "Apache-2.0"
//            }
//        }
//    }
//}


//config {
//    release = if (releaseActive != null) releaseActive!! else false
//
//    info {
//        name        = "Sample"
//        vendor      = "Acme"
//        description = "Sample project"
//
//        links {
//            website      = "https://github.com/joecool/sample"
//            issueTracker = "https://github.com/joecool/sample/issues"
//            scm          = "https://github.com/joecool/sample.git"
//        }
//
//        people {
//            person {
//                id    = "joecool"
//                name  = "Joe Cool"
//                roles = listOf("developer")
//            }
//        }
//    }
//
//    licensing {
//        licenses {
//            license {
//                id = org.kordamp.gradle.plugin.base.model.LicenseId.APACHE_2_0
//            }
//        }
//    }
//
//    bintray {
//        credentials {
//            username = project.bintrayUsername
//            password = project.bintrayApiKey
//        }
//        userOrg    = "joecool"
//        name       = rootProject.name
//        githubRepo = "joecool/sample"
//    }
//}
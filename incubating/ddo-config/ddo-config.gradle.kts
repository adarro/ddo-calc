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
import org.unbrokendome.gradle.plugins.testsets.dsl.testSets

description = "Configuration Library used to initialize and bootstrap DDO services"


plugins {
    id("scala-profiles")
}

// import common scala project dependencies etc
project.apply { from(rootProject.file("gradle/scala.gradle.kts")) }

testSets {
    "acceptanceTest" {
        dirName = "specs"
        sourceSet.java {
            "acceptanceTest" {
                exclude(
                    "**/*Spec.scala",
                    // "**/*Helper*",
                    "**/*Builder*"
                )
            }
        }


        sourceSet.resources {
            this.srcDirs.add(File("${project.projectDir}/src/test/specs"))
        }
        configurations[compileClasspathConfigurationName].extendsFrom(configurations["testImplementation"])
        createArtifact = true
    }
}

/*
  Don't add fully qualified dependencies here.
  Any new dependencies should be placed in platform-ddo-scala then referenced in scala.build.kts if it will be used by other components of ddo or here if it is truly unique to this artifact.
 */
 dependencies {
    implementation(project(":ddo-util"))
  //  api(platform(":ddo-platform-scala"))
}

// val crossBuild =  project.extensions.create("crossBuild", CrossBuildExtension, project)
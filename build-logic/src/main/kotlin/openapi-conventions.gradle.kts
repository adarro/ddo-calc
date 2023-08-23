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
import io.swagger.v3.plugins.gradle.tasks.ResolveTask


plugins {
    java apply (false)
    id("io.swagger.core.v3.swagger-gradle-plugin")
}
//if (project.pluginManager.findPlugin("java") === null)
//    project.apply { java }
tasks.withType<ResolveTask>().configureEach {
  //  outputFileName = "PetStoreAPI"
//    //outputFormat = "JSONANDYAML"
    outputFormat = ResolveTask.Format.JSONANDYAML
    prettyPrint = true

//    if (project.pluginManager.findPlugin("java") == null) {
//        project.plugins.apply(JavaPlugin::class.java)
//    }

    val sourceSets = project.properties["sourceSets"] as SourceSetContainer?
    val mainSourceSet = sourceSets!!.getByName("main")
    classpath = mainSourceSet.runtimeClasspath
//    openApiFile =
//        project.file("src/main/resources/openapi-configuration.json") // getClass().getClassLoader().getResource("database.properties").getFile()

    resourcePackages = setOf("io.swagger.v3.plugins.grudle.petstore")
    outputDir = file(project.buildDir)
}
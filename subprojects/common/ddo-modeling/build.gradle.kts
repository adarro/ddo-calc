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
    id("scala-library-profile")
    id("djaxonomy.test-conventions")
    id("com.zlad.gradle.avrohugger")
//    id("com.github.lkishalmi.gatling") version "3.2.9"
    //  id("io.gatling.gradle") version "3.9.5.5" replaces above
    id("org.openapi.generator")
}

dependencies {
    /*
    https://github.com/fthomas/refined
    check out refined library for compile time constraints
    unsure how  helpful this will be as most data will need runtime validation (aka wix)
     */
    // Use Scala $scalaMajorVersion in our library project
    val scalaLibraryVersion: String by project
    val scalaMajorVersion: String by project

    implementation(enforcedPlatform(project(":ddo-platform-scala")))
    implementation("org.scala-lang:scala-library:$scalaLibraryVersion")
    implementation(group = "com.beachape", name = "enumeratum_$scalaMajorVersion")
    implementation(group = "com.typesafe", name = "config")
    implementation(group = "com.github.kxbmap", name = "configs_$scalaMajorVersion")

    implementation(group = "org.json4s", name = "json4s-native_$scalaMajorVersion")

    // validation and rules
    implementation(group = "com.wix", name = "accord-core_$scalaMajorVersion")
    implementation(group = "ch.qos.logback", name = "logback-classic")
    implementation(group = "com.typesafe.scala-logging", name = "scala-logging_$scalaMajorVersion")
//    testImplementation(group = "org.scalatest", name = "scalatest_$scalaMajorVersion")
//    testImplementation(group = "org.mockito", name = "mockito-core")
//
//    // JUnit 5
//    testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-engine")
//    testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-launcher")
//    testRuntimeOnly(group = "co.helmethair", name = "scalatest-junit-runner")
}

// OpenApi code / schema generation

val apiSpec: FileCollection = project.rootProject.layout.files("$rootDir/specs/ddo-fatespinner-oas3-swagger.yaml")
// Location of Avro schema files
val schemaDir: FileCollection = layout.files("src/main/resources/schemas/avro")
// Location of ??
val generatedScalaSourceDir = layout.files("src/main/avro")

/**
 * Api spec OpenAPI specification generation information
 *
 * @property spec Name of the specification
 * @property schemaDir Directory of schema files
 * @property generatedSrcDir destination for generated source code directory
 * @constructor a new instance
 */
data class ApiSpec(
    val spec: String,
    val schemaDir: String,
    val generatedSrcDir: String,
)

/**
 * Package spec holds the basic header information for the API
 *
 * @property basePackage base java package i.e. com.acme
 * @property apiPackage package name for generated source.  This will be appended to basePackage.
 * @property invokerPackage package name for invoker classes
 * @property modelPackage package name for entity models
 * @constructor Creates a new API specification
 */
@Suppress("CUSTOM_GETTERS_SETTERS", "NO_CORRESPONDING_PROPERTY")
data class PackageSpec(
    val basePackage: String = "io.truthencode.ddo",
    val apiPackage: String = "api",
    val invokerPackage: String = "invoker",
    val modelPackage: String = "models.model",
) {
    /**
     * Api qualified api package name
     */
    val api: String
        get() {
            return "$basePackage.$apiPackage"
        }

    /**
     * Invoker qualified invoker package
     */
    val invoker: String
        get() {
            return "$basePackage.$invokerPackage"
        }

    /**
     * Model qualified model package
     *
     */
    val model: String
        get() {
            return "$basePackage.$modelPackage"
        }
}

val defaultApiSpec = ApiSpec(apiSpec.asPath, schemaDir.asPath, generatedScalaSourceDir.asPath)
// val defaultPackageSpec =
// PackageSpec("io.truthencode.ddo.api","io.truthencode.ddo.invoker","io.truthencode.ddo.models.model")
val schemas =
    mapOf("ddoModel" to defaultApiSpec, "parseHub" to defaultApiSpec.copy(spec = "$rootDir/specs/parsehub.yaml"))
val specs = mapOf("parseHub" to PackageSpec(basePackage = "io.truthencode.ddo.etl.parsehub"))

openApiValidate {
    inputSpec.set(apiSpec.asPath)
}

avrohugger {
    this.sourceDirectories {
        this.from(schemaDir)
    }
    this.destinationDirectory.set(generatedScalaSourceDir.singleFile)
    typeMapping {
        protocolType = com.zlad.gradle.avrohugger.AvrohuggerExtension.ScalaADT
        enumType = com.zlad.gradle.avrohugger.AvrohuggerExtension.ScalaCaseObjectEnum
    }
}
val schemaList = listOf("parseHub")

// Create Tasks to generate Avro Schemas for our OpenAPI specs

val genAvroSchemaTask =
    task("genAvroSchema") {
        this.group = "OpenAPI Tools"
        dependsOn("openApiValidate")
    }

run {
    @Suppress("IDENTIFIER_LENGTH")
    schemaList.forEach { id ->
        val name = "genAvroSchema$id"
        tasks.create(name, org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class).apply {
            generatorName.set("avro-schema")
            schemas[id]?.let { s ->
                inputSpec.set(s.spec)
                outputDir.set(s.schemaDir)
            }
            specs[id]?.let { p ->
                apiPackage.set(p.api)
                invokerPackage.set(p.invoker)
                modelPackage.set(p.model)
            }
            this.group = "OpenAPI Tools"
            dependsOn("openApiValidate")
            genAvroSchemaTask.dependsOn(this)
        }
    }
}

task("genModel", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
    verbose.set(true)
    generatorName.set("scala-lagom-server")
    inputSpec.set(apiSpec.asPath)
    outputDir.set(layout.buildDirectory.dir("generated/lagom").get().asFile.path)

    apiPackage.set("io.truthencode.ddo.api")
    invokerPackage.set("io.truthencode.ddo.invoker")
    modelPackage.set("io.truthencode.ddo.models.model")
}

task("genGatling", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
    verbose.set(true)
    val id = "scala-gatling"
    generatorName.set(id)
    inputSpec.set(apiSpec.asPath)
    outputDir.set(layout.buildDirectory.dir("generated/$id").get().asFile.path)

    apiPackage.set("io.truthencode.ddo.api")
    invokerPackage.set("io.truthencode.ddo.invoker")
    modelPackage.set("io.truthencode.ddo.models.model")
}

tasks.register<Delete>("cleanAvroSchema") {
    description = "Clears generated Schemas Directory"
    group = "source generation"
    delete = setOf(schemaDir)
}

tasks.register<Delete>("cleanGeneratedScala") {
    description = "Cleans generated scala directory"
    group = "source generation"
    delete = setOf(generatedScalaSourceDir)
}

val cleanTask = tasks.named("clean")

if (cleanTask.isPresent) {
    cleanTask.get().dependsOn("cleanGeneratedScala")
}

tasks {
    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform {
            includeEngines = setOf("scalatest")
            testLogging {
                events("passed", "skipped", "failed")
            }
        }
    }
//    val sv = named("syncVersionFiles").get()
//    // BUG: should not need to declare task dependencies when tasks use non-conflicting outputs
//    val gas = named("generateAvroScala").get()
//    gas.mustRunAfter(named("syncVersionFiles"))
//    withType<com.hierynomus.gradle.license.tasks.LicenseCheck> {
//        mustRunAfter(gas)
//    }
}
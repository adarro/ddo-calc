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
import com.zlad.gradle.avrohugger.AvrohuggerExtension

plugins {
    application
    id("scala-application-profile")
    id("com.zlad.gradle.avrohugger")
    id("com.github.lkishalmi.gatling") version "3.2.9"
    id("org.openapi.generator")
//    id("integration-test-conventions")
}

dependencies {
    // Use Scala $scalaMajorVersion in our library project
    val scalaLibraryVersion: String by project
    val scalaMajorVersion: String by project
    val airframeVersion: String by project
    val pulsarVersion: String by project
    val camelVersion: String by project
    val junitPlatformVersion: String by project
    val pulsar4sVersion: String by project // 2.8.1
    val monixVersion: String by project

    implementation(platform(project(":ddo-platform-scala")))
    implementation(project(":ddo-modeling"))
    testImplementation(project(":ddo-testing-util"))

    implementation(dependencyNotation = "org.scala-lang:scala-library:$scalaLibraryVersion")
    implementation(group = "com.beachape", name = "enumeratum_$scalaMajorVersion")
    implementation(group = "com.typesafe", name = "config")
    implementation(group = "com.github.kxbmap", name = "configs_$scalaMajorVersion")

    implementation(group = "org.json4s", name = "json4s-native_$scalaMajorVersion")
    // DI
    implementation("org.wvlet.airframe:airframe_$scalaMajorVersion:$airframeVersion")
    implementation("org.wvlet.airframe:airframe-config_$scalaMajorVersion:$airframeVersion")
    implementation("org.wvlet.airframe:airframe-http-finagle_$scalaMajorVersion:$airframeVersion")

    // Atomic support
    implementation("org.scala-stm:scala-stm_2.13:0.11.1")

    // Monix
    // Monix-bio is new and isn't version aligned with others
    val monixBioVersion = "1.2.0"
    implementation(group = "io.monix", name = "monix-eval_$scalaMajorVersion", version = monixVersion)
    implementation(group = "io.monix", name = "monix-execution_$scalaMajorVersion", version = monixVersion)
    implementation(group = "io.monix", name = "monix-bio_$scalaMajorVersion", version = monixBioVersion)

    // MQ++ (Pulsar)
    implementation(group = "org.apache.pulsar", name = "pulsar-client-all", version = pulsarVersion)

    // https://mvnrepository.com/artifact/com.sksamuel.pulsar4s/pulsar4s-core
    implementation(
        group = "com.clever-cloud.pulsar4s",
        name = "pulsar4s-core_$scalaMajorVersion",
        version = pulsar4sVersion,
    )
    implementation(
        group = "com.clever-cloud.pulsar4s",
        name = "pulsar4s-monix_$scalaMajorVersion",
        version = pulsar4sVersion,
    )
    implementation(
        group = "com.clever-cloud.pulsar4s",
        name = "pulsar4s-avro_$scalaMajorVersion",
        version = pulsar4sVersion,
    )

    testImplementation("org.testcontainers:pulsar:1.16.2")
    testImplementation("org.apache.camel:camel-testcontainers-junit5:$camelVersion")

    testImplementation("org.testcontainers:mongodb:1.16.2")
    testImplementation("org.testcontainers:junit-jupiter:1.16.2")

    // validation and rules
    implementation(group = "com.wix", name = "accord-core_$scalaMajorVersion")
    implementation(group = "ch.qos.logback", name = "logback-classic")
    implementation(group = "com.typesafe.scala-logging", name = "scala-logging_$scalaMajorVersion")
    testImplementation(group = "org.scalatest", name = "scalatest_$scalaMajorVersion")
//    testImplementation(group = "org.mockito", name = "mockito-core")
    testImplementation("org.mockito:mockito-scala-scalatest_$scalaMajorVersion:1.16.49")

// go camel
    implementation("org.apache.camel:camel-core:$camelVersion")
    implementation("org.apache.camel:camel-pulsar:$camelVersion")
    implementation(dependencyNotation = "org.apache.camel:camel-componentdsl:$camelVersion")
    implementation("org.apache.camel:camel-main:$camelVersion")

    // JUnit 5
    testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-engine")
    testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-launcher")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine")
    testRuntimeOnly(group = "co.helmethair", name = "scalatest-junit-runner")

//    val integrationTestImplementation by configurations.getting
    // integrationTestImplementation.extendsFrom(configurations["testCompileClasspath"])
    // Testing out airspec, mainly for airframe DI IT testing purposes
    // https://mvnrepository.com/artifact/org.wvlet.airframe/airspec
//    integrationTestImplementation("org.wvlet.airframe:airspec_$scalaMajorVersion:$airframeVersion")
}

application {
    mainClass.set("io.truthencode.ddo.subscription.ProviderServices")
}

task("runClient", JavaExec::class) {
    group = "application"
    mainClass.set("io.truthencode.ddo.subscription.service.EchoClient")
    classpath = sourceSets["main"].runtimeClasspath
}
// OpenApi code / schema generation

val apiSpec: FileCollection = project.rootProject.layout.files("specs/ddo-fatespinner-oas3-swagger.yaml")
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
        protocolType = AvrohuggerExtension.ScalaADT
        enumType = AvrohuggerExtension.ScalaCaseObjectEnum
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

tasks.create<Delete>("cleanAvroSchema") {
    description = "Clears resource Schemas Directory"
    group = "source generation"
    delete = setOf(schemaDir)
}

tasks.create<Delete>("cleanGeneratedScala") {
    description = "Cleans generated scala directory"
    group = "source generation"
    delete = setOf(generatedScalaSourceDir)
}

// tasks.withType(com.hierynomus.gradle.license.tasks.LicenseFormat::class) {
//    excludes.add("src/main/avro/**/*.scala")
// }

tasks.getAt("clean").dependsOn("cleanGeneratedScala")

tasks {
    // Use the built-in JUnit support of Gradle.

    "test"(Test::class) {
        useJUnitPlatform {
            includeEngines = setOf("scalatest", "junit-jupiter")
            if (ci.isCi) {
                excludeTags =
                    setOf(
                        "Integration",
                        "io.truthencode.tags.Integration",
                        "FunctionOnly",
                        "io.truthencode.tags.FunctionOnly",
                    )
            }
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
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

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
    id("buildlogic.scala-library-profile")
    id("buildlogic.test-conventions")
//     id("com.zlad.gradle.avrohugger")
//    id("com.github.lkishalmi.gatling")
    //  id("io.gatling.gradle") version "3.9.5.5" replaces above
    id("org.openapi.generator")
//    id("code-quality")

//    id("io.quarkus")//
}
// apply {
//    plugin("io.quarkus")// version "3.3.3"
// }

// OpenApi code / schema generation

val apiSpec: FileCollection = project.rootProject.layout.files("$rootDir/specs/ddo-fatespinner-oas3-swagger.yaml")
// Location of Avro schema files
val schemaDir: FileCollection = layout.files("src/main/resources/schemas/avro")
// Location of Avro generated Scala files from ddo-avro external build
val generatedScalaSourceDir = layout.buildDirectory.files("avro-gen")
// val ff =project.rootProject.layout.files("../../subprojects/common/ddo-model/build/avro-gen")
val CODE_GEN = "codeGen"
// External builds
// Calling as external build due to scala library version incompatibilities
// between AvroHugger (scala 2.12.1?) and Quarkus 2.13.x / 3)
tasks.register("generateAvroSchemas", GradleBuild::class) {
    description = "Generates Avro schemas"
    group = "Avro"
    val output = layout.buildDirectory.dir("avro-gen")
    outputs.dir(output)
    val input =
        rootProject.layout.projectDirectory
            .file("include/ddo-avro")
            .asFile
    inputs.dir(input)
    dir =
        rootProject.layout.projectDirectory
            .file("include/ddo-avro")
            .asFile

    tasks = listOf("generateAvroScala")
}

tasks.register("cleanAvroSchemas", GradleBuild::class) {
    description = "Cleans generated Avro schemas"
    group = "Avro"
    dir =
        rootProject.layout.projectDirectory
            .file("include/ddo-avro")
            .asFile

    tasks = listOf("clean")
}

@Suppress("UnstableApiUsage")
configurations {
    val codeGen by configurations.creating {
        isCanBeConsumed = false
        isCanBeResolved = true
    }
}

sourceSets {
    this.configureEach {
        scala {

            this.srcDir(tasks.named("generateAvroSchemas"))
        }
    }
//    scala {
//        this.srcDir(tasks.named("generateAvroSchemas"))
//    }
//   val codeGen by creating {
//       scala {
//           this.srcDir(tasks.named("generateAvroSchemas"))
//       }
//   }
}

// tasks.named("compileCodeGenScala",ScalaCompile::class) {
//    classpath = configurations.named(CODE_GEN).get()
// }

// TODO: Build Chore
// Configure proper task dependency and remove explicit depends on for Avro Generation
tasks.named("clean") {
    dependsOn("cleanAvroSchemas")
}

tasks.named("compileScala") {
    dependsOn("generateAvroSchemas")
}

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

// avrohugger {
//     this.sourceDirectories {
//         this.from(schemaDir)
//     }
//     this.destinationDirectory.set(generatedScalaSourceDir.singleFile)
//     typeMapping {
//         protocolType = com.zlad.gradle.avrohugger.AvrohuggerExtension.ScalaADT
//         enumType = com.zlad.gradle.avrohugger.AvrohuggerExtension.ScalaCaseObjectEnum
//     }
// }
val schemaList = listOf("parseHub")

// Create Tasks to generate Avro Schemas for our OpenAPI specs

val genAvroSchemaTask =
    tasks.register("genAvroSchema", fun Task.() {
        this.group = "OpenAPI Tools"
        dependsOn("openApiValidate")
    })

run {
    @Suppress("IDENTIFIER_LENGTH")
    schemaList.forEach { id ->
        val name = "genAvroSchema$id"
        tasks.register(name, org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
            description = "Generates Avro Schema for $id"
            group = "OpenAPI Tools"
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
            genAvroSchemaTask.get().dependsOn(this)
        }
    }
}

tasks.register("genModel", GenerateTask::class, fun GenerateTask.() {
    verbose.set(true)
    generatorName.set("scala-lagom-server")
    inputSpec.set(apiSpec.asPath)
    outputDir.set(
        layout.buildDirectory
            .dir("generated/lagom")
            .get()
            .asFile.path,
    )

    apiPackage.set("io.truthencode.ddo.api")
    invokerPackage.set("io.truthencode.ddo.invoker")
    modelPackage.set("io.truthencode.ddo.models.model")
})

tasks.register("genGatling", GenerateTask::class, fun GenerateTask.() {
    verbose.set(true)
    val id = "scala-gatling"
    generatorName.set(id)
    inputSpec.set(apiSpec.asPath)
    outputDir.set(
        layout.buildDirectory
            .dir("generated/$id")
            .get()
            .asFile.path,
    )

    apiPackage.set("io.truthencode.ddo.api")
    invokerPackage.set("io.truthencode.ddo.invoker")
    modelPackage.set("io.truthencode.ddo.models.model")
})

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

dependencies {
    /*
    https://github.com/fthomas/refined
    check out refined library for compile time constraints
    unsure how  helpful this will be as most data will need runtime validation (aka wix)
     */
    // Use Scala $scalaMajorVersion in our library project
    val builderScalaVersion: String by project
//    implementation(enforcedPlatform(project(":ddo-platform-scala")))
    when (builderScalaVersion) {
        "3" -> {
            implementation(libs.scala3.library)
            implementation(libs.enumeratum.s3)

            implementation(libs.json4s.native.s3)

            implementation(libs.typesafe.scala.logging.s3)
            // replacing wix accord validation with zio-prelude validation
//            implementation(libs.wix.accord.core.s213)
            implementation(libs.dev.zio.prelude.s3)
            implementation(libs.kxbmap.configs.s213)
        }

        else -> {
            implementation(libs.scala2.library)
            implementation(libs.scala2.library)
            implementation(libs.enumeratum.s213)

            implementation(libs.kxbmap.configs.s213)

            implementation(libs.json4s.native.s213)

            // validation and rules
            // replacing wix accord validation with zio-prelude validation
//            implementation(libs.wix.accord.core.s213)
            implementation(libs.dev.zio.prelude.s213)
            implementation(libs.typesafe.scala.logging.s213)
        }
    }

    implementation(libs.typesafe.config)

    implementation(libs.logback.classic)
}
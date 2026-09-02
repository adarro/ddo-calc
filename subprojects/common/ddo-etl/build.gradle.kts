// import io.truthencode.buildlogic.BuildEnvironment
// import io.truthencode.buildlogic.getBuildEnvironment

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
//    id("scala-conventions")
//    id("buildlogic.scala-library-profile")
    id("buildlogic.java-library-conventions")
    // alias(libs.plugins.quarkus)
    // `java-library`
    id("buildlogic.quarkus-scala-conventions")
//   id("buildlogic.quarkus-common-conventions")

//    id("acceptance-test-conventions")
    id("buildlogic.test-conventions")
//    `jvm-test-suite`
}

repositories {
    mavenCentral()
}

description = "Common ETL module for storing / loading data from web / user etc"

dependencies {

    implementation(platform(project(":ddo-platform-scala")))
    implementation(project(":ddo-antlr"))
    implementation(project(":ddo-web")) {
        because("ddo-web is used for web scraping")
    }
    implementation(project(":ddo-core"))
    implementation(libs.io.jstach.jstachio) {
        because("template engine for sql import etc")
    }
//    implementation(libs.jline.core) {
//        because("attempting to force quarkus to not load jdk8 variant")
// //        this.capabilities {
// //            this.requireCapability("java-api")
// //        }
//    }
//    implementation(libs.jline.jansi) {
//        because("attempting to force quarkus to not load jdk8 variant")
//    }
    implementation(libs.quarkus.freemarker)
    implementation(libs.smallrye.mutiny.vertx.client)
    implementation(libs.quarkus.openapi)
    implementation(libs.quarkus.rest)
//    // Additional features for production build
//    if (getBuildEnvironment() == BuildEnvironment.PROD) {
//        implementation(libs.quarkus.smallrye.metrics)
//    }

    // uncomment closer to production
    implementation(libs.quarkus.smallrye.context.propagation)
    implementation(libs.quarkus.mutiny)
   /*
   might use https://github.com/nrinaudo
    for etl regex support
    xpath and csv also
    scala 2x only

    Also, monocle for idiomatic updating of immutable objects (i.e. case classes)
    https://www.optics.dev/Monocle/
    (Need scala 3 to update optional fields and single element in list)
    */

    // https://mvnrepository.com/artifact/org.json4s/json4s-native
//    val builderScalaVersion = providers.gradleProperty("builderScalaVersion").getOrElse(FALLBACK_SCALA_VERSION)

//    val myExtension = project.extensions.getByType<ScalaBuildExtension>()

    implementation(libs.avro.tools) {
        // CVE-2023-36478 https://www.mend.io/vulnerability-database/CVE-2023-36478?utm_source=JetBrains
        this.exclude(module = "eclipse.jetty:jetty-http:_")
    }
    // 12.0.12
    implementation(libs.apache.hadoop.common)
    implementation(libs.jayway.jsonpath)
    // Jetbrains Xodus embedded database
    implementation(libs.bundles.xodus)
    // "xodus-crypto",
    // "xodus-entity-store",
    // "xodus-environment",
    // "xodus-openAPI",
    // "xodus-vfs"
    implementation(libs.typesafe.config)
    implementation(libs.logback.classic)
    // TODO: add scala logging s3 dependency
    //    May have been transitively included but should be explicitly declared
    implementation(libs.scala.logging.s3)
    // Tags for tests
//    testImplementation(project(":ddo-testing-util"))
}

scalaBuildInfo {
    scalaVersion = "3"
}
// tasks.withType<Test> {
//     useJUnitPlatform ()
// }
//
// tasks.withType<Test> {
// //    useJUnitPlatform {
// //        includeTags("io.quarkus.test.junit.QuarkusTest", "Unit")
// //    }
// }

// Configuration to handle JLine dependency substitution
// Quarkus is looking for a ghost jdk8 classified version of JLine

// known configurations that are looking for jline-jdk8 variants which don't exist in Jline 4+
// val jLineNaughtyList = listOf("quarkusProdRuntimeClasspathConfiguration", "quarkusConditionalDevRuntimeClasspath")
//
// configurations.named("quarkusProdRuntimeClasspathConfiguration") {
//    attributes {
//        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class.java, Usage.JAVA_RUNTIME))
//    }
// }
//
// configurations.named("quarkusConditionalDevRuntimeClasspath") {
//    attributes {
//        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class.java, Usage.JAVA_RUNTIME))
//    }
// }
//
// // force all jline-jdk8 variants to be plain java runtime
// configurations.all {
//    if (name in jLineNaughtyList) {
//        logger.warn("Forcing $name to be a plain java runtime")
//        attributes {
//            attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class.java, Usage.JAVA_RUNTIME))
//        }
//    }
// }

// testing {
//    suites {
//        withType(JvmTestSuite::class) {
//            this.useJUnitPlatform
//            // useJUnitPlatform
//            this.useJUnitJupiter() {
//
//            }
//        }
//    }
// }
// testing {
//    suites {
//        @Suppress("UnstableApiUsage")
//        withType(JvmTestSuite::class)
//            .matching { it.name in listOf("acceptanceTest") }
//            .configureEach {
//                dependencies {
//                    implementation(project(":ddo-modeling"))
//                }
//            }
//    }
// }

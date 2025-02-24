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
    id("buildlogic.scala-library-profile")
    id("buildlogic.java-library-conventions")
//    id("acceptance-test-conventions")
    id("buildlogic.test-conventions")
}

description = "Common ETL module for storing / loading data from web / user etc"

dependencies {
    implementation(enforcedPlatform(project(":ddo-platform-scala")))
    implementation(project(":ddo-antlr"))
    implementation(project(":ddo-web")) {
        because("ddo-web is used for web scraping")
    }
    implementation(project(":ddo-core"))
    /*
    might use  https://github.com/nrinaudo
     for etl regex support
     xpath and csv also
     scala 2x only

     Also, monocle for idiomatic updating of immutable objects (i.e. case classes)
     https://www.optics.dev/Monocle/
     (Need scala 3 to update optional fields and single element in list)
     */

    // https://mvnrepository.com/artifact/org.json4s/json4s-native
    val builderScalaVersion: String by project
    when (builderScalaVersion) {
        "3" -> {
            implementation(libs.scala3.library)
            implementation(libs.json4s.native.s3)
            implementation(libs.enumeratum.s3)

            /* DB, Query etc

    // Quill Scala Query QSQL
     https://github.com/getquill/quill */

            /* Quill - Monix Integration
    Monix Task / Eval https://monix.io/docs/current/intro/hello-world.html */
//            implementation(libs.quill.monix.s213)
            implementation(libs.quill.sql.s3)
//            implementation(libs.monix.eval.s213)
//            implementation(libs.monix.reactive.s213)

            // Jetbrains Xodus embedded database
//    implementation(libs.xodus.openAPI)

            // validation and rules
            implementation(libs.dev.zio.prelude.s3)
            implementation(libs.dev.zio.s3)

            implementation(libs.typesafe.scala.logging.s3)
        }

        else -> {
            implementation(libs.json4s.native.s213)

            implementation(libs.scala2.library)
            implementation(libs.enumeratum.s213)

            implementation(libs.kxbmap.configs.s213)

            /* DB, Query etc

    // Quill Scala Query QSQL
     https://github.com/getquill/quill */

            /* Quill - Monix Integration
    Monix Task / Eval https://monix.io/docs/current/intro/hello-world.html */
//            implementation(libs.quill.monix.s213)
            implementation(libs.quill.sql.s213)
//            implementation(libs.monix.eval.s213)
//            implementation(libs.monix.reactive.s213)

            // validation and rules

            implementation(libs.dev.zio.prelude.s213)
            implementation(libs.typesafe.scala.logging.s213)
        }
    }
    implementation(libs.avro.tools)
    implementation(libs.apache.hadoop.common)
    implementation(libs.jayway.jsonpath)
    // Jetbrains Xodus embedded database
    implementation("org.jetbrains.xodus:xodus-entity-store:2.0.1")
    implementation("org.jetbrains.xodus:xodus-crypto:2.0.1")
    implementation("org.jetbrains.xodus:xodus-vfs:2.0.1")
    implementation("org.jetbrains.xodus:xodus-environment:2.0.1")
    implementation(libs.xodus.openAPI) {
        because("Used for tracking entity update status")
    }
    implementation(libs.typesafe.config)
    implementation(libs.logback.classic)
}
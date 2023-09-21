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
    id("scala-conventions")
//    id("acceptance-test-conventions")
    id("djaxonomy.test-conventions")
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

     Also, monocle for idiomatic updating of immutable objects (i.e. case classes)
     https://www.optics.dev/Monocle/
     (Need scala 3 to update optional fields and single element in list)
     */
    val scalaLibraryVersion: String by project
    val scalaMajorVersion: String by project
    // https://mvnrepository.com/artifact/org.json4s/json4s-native
    implementation(libs.json4s.native.s213)

    implementation(libs.scala2.library)
    implementation(libs.enumeratum.s213)
    implementation(libs.typesafe.config)
    implementation(libs.kxbmap.configs.s213)

    /* DB, Query etc
    // Quill Scala Query QSQL
     https://github.com/getquill/quill */
    implementation(libs.quill.core.s213)
    /* Quill - Monix Integration
    Monix Task / Eval https://monix.io/docs/current/intro/hello-world.html */
    implementation(libs.quill.monix.s213)
    implementation(libs.quill.sql.s213)
    implementation(libs.monix.eval.s213)
    implementation(libs.monix.reactive.s213)

    // validation and rules
    implementation(libs.wix.accord.core.s213)
    implementation(libs.logback.classic)
    implementation(libs.typesafe.scala.logging.s213)
}
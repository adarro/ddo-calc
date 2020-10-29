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
// import com.moowork.gradle.node.task.NodeTask

plugins {
    scala
    `java-library`
    id ("org.springframework.boot") version "2.0.0.RELEASE"
  //  id ("com.moowork.node") version "1.2.0"
     id("org.siouan.frontend-jdk8")
 //   id("io.spring.dependency-management")
}

apply {
     plugin ("io.spring.dependency-management")
}

repositories {
    mavenCentral()
}

val jettyVersion by extra {"9.4.7.v20170914"} //"9.4.9.v20180320"

dependencies {
    testImplementation ("io.rest-assured:rest-assured:3.0.7")
    implementation(group = "org.scala-lang", name = "scala-library", version = "2.12.3")
    implementation("org.apache.olingo:odata-server-api:4.4.0")
    implementation("org.apache.olingo:odata-server-core:4.4.0")
    testCompile("junit:junit:4.12")
    implementation(group = "io.vertx", name = "vertx-core", version = "3.5.1")
    implementation(group = "io.vertx", name = "vertx-web", version = "3.5.1")
    testImplementation(group = "io.vertx", name = "vertx-unit", version = "3.5.1")
    testCompile("org.mockito:mockito-core:+")
    compileOnly(group = "org.projectlombok", name = "lombok", version = "1.16.20")
    implementation(group = "commons-io", name = "commons-io", version = "2.6")
    implementation("org.apache.commons:commons-lang3:3.7")
    implementation(group = "com.google.guava", name = "guava", version = "24.0-jre")
    // Spring
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-web", version = "2.0.0.RELEASE") {
        exclude(module= "spring-boot-starter-tomcat")
    }

    // Logging
    compile(group = "org.slf4j", name = "slf4j-api", version = "1.7.25")
    testCompile(group = "ch.qos.logback", name = "logback-classic", version = "1.2.3")
    implementation(group = "com.typesafe.scala-logging", name = "scala-logging_2.12", version = "3.9.2")

    // quick and dirty web testing
    testImplementation (group= "org.eclipse.jetty", name="jetty-server", version= jettyVersion)
    testImplementation (group= "org.eclipse.jetty", name="jetty-servlet", version= jettyVersion)

// Hopefully will be able to remove / replace servlet dependency for OData
    compileOnly(group = "javax.servlet", name = "javax.servlet-api", version = "4.0.0")
    testImplementation(group = "javax.servlet", name = "javax.servlet-api", version = "4.0.0")

    // testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

frontend {
    nodeDistributionProvided.set(false)
    nodeVersion.set("8.17.0")
    nodeDistributionUrlRoot.set("https://nodejs.org/dist/")
    nodeDistributionUrlPathPattern.set("vVERSION/node-vVERSION-ARCH.TYPE")
    nodeDistributionServerUsername.set("username")
    nodeDistributionServerPassword.set("password")
    nodeInstallDirectory.set(project.layout.projectDirectory.dir("node"))

    yarnEnabled.set(true)
    yarnDistributionProvided.set(false)
    yarnVersion.set("1.22.5")
    yarnDistributionUrlRoot.set("https://github.com/yarnpkg/yarn/releases/download/")
    yarnDistributionUrlPathPattern.set("vVERSION/yarn-vVERSION.tar.gz")
//    yarnDistributionServerUsername.set("username")
//    yarnDistributionServerPassword.set("password")
    yarnInstallDirectory.set(project.layout.projectDirectory.dir("yarn"))

    installScript.set("install")
    cleanScript.set("run clean")
    assembleScript.set("run assemble")
    checkScript.set("run check")
    publishScript.set("run publish")

    packageJsonDirectory.set(project.layout.projectDirectory.asFile)
//    proxyHost.set("127.0.0.1")
//    proxyPort.set(8080)
//    proxyUsername.set("username")
//    proxyPassword.set("password")
    verboseModeEnabled.set(true)
}

//task ("exportSwagger", NodeTask::class) {
//    group ="special"
//    setScript(file("src/main/script/dotransform.js"))
//    // ignoreExitValue = true
//}
//
//var localNodeHome = "${project.buildDir}/nodejs"
//var localNpmWorkDir = "${project.buildDir}/npm"
//var localYarnWorkDir = "${project.buildDir}/yarn"
//
//node {
//    // Version of node to use.
//    version = "0.11.10"
//
//    // Version of npm to use.
//    npmVersion = "2.1.5"
//
//    // Version of Yarn to use.
//    yarnVersion = "0.16.1"
//
//    // Base URL for fetching node distributions (change if you have a mirror).
//    distBaseUrl = "https://nodejs.org/dist"
//
//    // If true, it will download node using above parameters.
//    // If false, it will try to use globally installed node.
//    download = false
//
//    // Set the work directory for unpacking node
//    workDir = file(localNodeHome)
//
//    // Set the work directory for NPM
//    npmWorkDir = file(localNpmWorkDir)
//
//    // Set the work directory for Yarn
//    yarnWorkDir = file(localYarnWorkDir)
//
//    // Set the work directory where node_modules should be located
//    nodeModulesDir = file("${project.projectDir}")
//}
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
/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("scala-library-profile")
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://www.evosuite.org/m2")

        // isAllowInsecureProtocol = true
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

val scalaLibraryVersion: String by project
val scalaMajorVersion: String by project
val scalateVersion: String by project
val camelVersion: String by project
val vertxVersion: String by project
val logbackVersion: String by project
// lemonlabs uri manipulation
val lemonlabsVersion: String by project
val configsVersion: String by project
val scalaLoggingVersion: String by project
val slf4jVersion: String by project
val scalaTestVersion: String by project
val hazelcastVersion: String by project
val junitScalaTestVersion: String by project
val junitPlatformVersion: String by project
val junitLauncherVersion: String by project
val monixVersion: String by project

dependencies {
    implementation(platform(project(":ddo-platform-scala")))
    implementation(group = "io.monix", name = "monix-eval_$scalaMajorVersion", version = monixVersion)
    implementation(group = "io.monix", name = "monix-execution_$scalaMajorVersion", version = monixVersion)
    implementation("com.hazelcast:hazelcast:$hazelcastVersion")
    implementation("org.scala-lang:scala-library:$scalaLibraryVersion")
    implementation("org.scala-lang:scala-reflect:$scalaLibraryVersion")
    implementation("org.scala-lang:scala-compiler:$scalaLibraryVersion")
    implementation("com.typesafe.akka:akka-actor_$scalaMajorVersion:2.6.16")
    implementation("org.scalatra.scalate:scalate-core_$scalaMajorVersion:$scalateVersion")
    implementation("io.lemonlabs:scala-uri_$scalaMajorVersion:$lemonlabsVersion")

    implementation(group = "com.beachape", name = "enumeratum_$scalaMajorVersion")
    // Logging
    implementation("com.tersesystems.blindsight:blindsight-logstash_$scalaMajorVersion:1.5.2")
    // https://mvnrepository.com/artifact/com.tersesystems.logback/logback-structured-config
    implementation("com.tersesystems.logback:logback-structured-config:1.0.1")
    compileOnly("org.slf4j:jul-to-slf4j:$slf4jVersion")
    // implementation(group = "ch.qos.logback", name = "logback-classic")
    // implementation(group = "com.typesafe.scala-logging", name = "scala-logging_$scalaMajorVersion")
    implementation("com.typesafe:config:1.3.0")
    implementation("com.github.kxbmap:configs_$scalaMajorVersion:$configsVersion")
    implementation("io.vertx:vertx-core:$vertxVersion")
    implementation("io.vertx:vertx-web:$vertxVersion")
    implementation("io.vertx:vertx-mongo-client:$vertxVersion")
    implementation("io.vertx:vertx-zookeeper:$vertxVersion")
    // implementation("io.vertx:vertx-mongo-embedded-db:$vertxVersion")
//    implementation("io.vertx:vertx-hazelcast:$vertxVersion") {
//        exclude(group="com.hazelcast", module = "hazelcast")
//    }
    implementation("io.vertx:vertx-codegen:$vertxVersion")
    // implementation("io.vertx:vertx-lang-js:$vertxVersion")
    implementation("io.vertx:vertx-auth-shiro:$vertxVersion")
    implementation("org.apache.shiro:shiro-core:1.8.0")
    implementation("org.apache.camel:camel-core:$camelVersion")
    implementation("org.apache.camel:camel-vertx:$camelVersion")
    implementation("org.apache.camel:camel-vertx:$camelVersion")
    implementation("org.apache.camel:camel-pulsar:$camelVersion")
    implementation("org.apache.camel:camel-main:$camelVersion")
    /* swapping to Zookeeper over hazelcast due to Java 9+ issues + Pulsar is using Zookeeper
    So we can attempt to save a bit of overhead
     */
//    implementation("org.apache.camel:camel-hazelcast:$camelVersion") {
//        exclude(group="com.hazelcast", module = "hazelcast")
//    }
    implementation("org.javassist:javassist:3.20.0-GA")
    // testImplementation("org.scalatra.scalate:scalate-test_$scalaMajorVersion:$scalateVersion")
    testImplementation("org.apache.httpcomponents:httpclient:4.5.13")
    testImplementation("org.evosuite:evosuite-standalone-runtime:1.0.5")
    testImplementation("org.scalatest:scalatest_$scalaMajorVersion:$scalaTestVersion")

    // Uri mangling (formerly netaporter)
    // https://mvnrepository.com/artifact/io.lemonlabs/scala-uri
    implementation("io.lemonlabs:scala-uri_2.13:4.0.0-M3")

    // testImplementation("io.vertx:vertx-core:$vertxVersion")

    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:$junitPlatformVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-engine", version = junitLauncherVersion)
    // testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-launcher", version = junitLauncherVersion)
    // // Scalatest
    testRuntimeOnly(group = "co.helmethair", name = "scalatest-junit-runner", version = junitScalaTestVersion)
    // Legacy tests
    testRuntimeOnly(group = "org.junit.vintage", name = "junit-vintage-engine", version = junitPlatformVersion)
    // Jupyter Extensions
    // testImplementation("org.junit.jupiter:junit-jupiter:$junitPlatformVersion")
    // testImplementation("org.junit.jupiter:junit-jupiter-params:$junitPlatformVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    // testRuntimeOnly("org.junit.platform:junit-platform-runner:$junitLauncherVersion")

    // Vertx Junit integration
    testImplementation("io.vertx:vertx-junit5:$vertxVersion")
    testImplementation("io.vertx:vertx-web-client:$vertxVersion")

    // Embed web server for testing// https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-server
    testImplementation("org.eclipse.jetty:jetty-server:11.0.7")

    // testImplementation("io.vertx:vertx-unit:$vertxVersion")
    // old mongo embed works with < 12.12 may need different provider
    // testImplementation("com.github.simplyscala:scalatest-embedmongo_$scalaMajorVersion:0.2.4")
}

group = "io.truthencode"
version = "0.0.1-SNAPSHOT"
description = "TOAD Api"
//java.sourceCompatibility = JavaVersion.VERSION_11
//java.targetCompatibility = JavaVersion.VERSION_11

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks {
    withType(Test::class.java) {
        useJUnitPlatform {
            includeEngines = setOf("scalatest", "junit-jupyter")
            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        outputs.upToDateWhen { false }
    }
    // logger.info("concordion output directory: *************************************")
    // // Use the built-in JUnit support of Gradle.
    // "test"(Test::class) {
    // 
    // }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

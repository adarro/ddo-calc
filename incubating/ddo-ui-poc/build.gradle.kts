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
    scala

    // Apply the java-library plugin for API and implementation separation.
    application
    // id("org.javafxports.jfxmobile")
    id("org.openjfx.javafxplugin")
    //id("ch.epfl.scala:gradle-bloop_2.12") version "1.4.4"

    //   id ("org.javamodularity.moduleplugin") version "1.7.0"

}
buildscript {
    dependencies {

        classpath("ch.epfl.scala:gradle-bloop_2.12:1.4.4")
    }
}

// apply(plugin="bloop")

val sc = "11"
val tc = "11"

interface JdkVersionInfo {
    val isPreNine: Boolean
    val supportsModules: Boolean
}

abstract class ModuleSupportInfo(open val version: Int) : JdkVersionInfo {
    override val isPreNine: Boolean
        get() = version < 9
    override val supportsModules: Boolean
        get() = !isPreNine
}

data class VersionCompatibility(override val version: Int) : ModuleSupportInfo(version)

data class JdkVersion(val sourceCompatibility: Int, val targetCompatibility: Int) : JdkVersionInfo {
    private val sourceInfo: VersionCompatibility
        get() = VersionCompatibility(sourceCompatibility)
    private val targetInfo: VersionCompatibility
        get() = VersionCompatibility(targetCompatibility)
    override val isPreNine: Boolean
        get() = sourceInfo.isPreNine || targetInfo.isPreNine
    override val supportsModules: Boolean
        get() = sourceInfo.supportsModules && targetInfo.supportsModules
}

val osn = System.getProperty("os.name")
val osName = when {
    osn.startsWith("Linux") -> "linux"
    osn.startsWith("Mac") -> "mac"
    osn.startsWith("Windows") -> "win"

//    case n if n.startsWith("Windows") => "win"
//    else -> new
//    case n if n.startsWith("Mac") => "mac"
//    case n if n.startsWith("Windows") => "win"
//    case  => throw new Exception("Unknown platform!")
    else -> throw UnsupportedOperationException("This build requires a Linux, Max or Windows platform to run")
}


/**
 * Adds JavaFX dependencies
 * @note only JDK 11+ should add dependencies.  These were included in SDK in prior versions.
 */
fun javaOpenFXModules(dhs: DependencyHandlerScope, scope: String = "implementation") {
    val info = JdkVersion(sc.toInt(), tc.toInt())
    // should pull this from javafx modules plugin
    val mods = listOf("base", "controls", "fxml", "graphics", "media", "swing", "web")
    mods.forEach { m -> dhs.dependencies.add(scope, "org.openjfx:javafx-$m:$tc:$osName") }
}

/**
 * Adds SalaFX dependencies
 * This will be a lot smarter once moved to a plugin and should then also use the javafx plugin version
 */
fun scalaFXModules(dhs: DependencyHandlerScope, scope: String = "implementation") {
    val info = JdkVersion(sc.toInt(), tc.toInt())
    val scalaFxVersion: String by project
    val scalaFxVersion8: String by project
    val scalaFxVersion10: String by project
    val scalaMajorVersion: String by project
    val vals = mapOf(
        8..9 to scalaFxVersion8,
        10..10 to scalaFxVersion10,
        11..15 to scalaFxVersion
    )
    // Get mimimum version of JDK used between source and target
    val jdkV = listOf(sc, tc).sorted().first().toInt()
    val rslt = vals.filter { p -> p.key.contains(jdkV) }.map { v -> v.value }.firstOrNull()
    if (!rslt.isNullOrEmpty()) {
        dhs.add(scope, "org.scalafx:scalafx_${scalaMajorVersion}:${rslt}")
    }

}

//allprojects {
//    apply(plugin = "bloop")
//}

//java {
//    modularity.inferModulePath.set(true)
//}
//configurations {
//    scalaCompiler
//}
//configurations.scalaCompiler.transitive = false
//
//
//compileScala.targetCompatibility = "1.8"

repositories {
    mavenCentral()
    jcenter()
    mavenLocal()
}

tasks.register("showPlugins") {
    project.plugins.forEach {
        println(it)


    }
}

dependencies {
    val scalaLibraryVersion: String by project
    val scalaMajorVersion: String by project
    val scalaFxVersion: String by project
    val scalaFXMLVersion: String by project
    val enumeratumVersion: String by project
    implementation("org.scala-lang:scala-library:$scalaLibraryVersion")
    //  implementation(group = "", name = "_${scalaMajorVersion}")
    implementation(group = "org.jooq", name = "joor", version = "0.9.13")
    //  implementation(project(":ddo-calc:ddo-core"))
    // implementation(project("../../subprojects/common/ddo-core"))
    implementation(group = "io.truthencode", name = "ddo-core", version = "0.0.1")
    implementation(group = "com.beachape", name = "enumeratum_${scalaMajorVersion}", version = enumeratumVersion)
    // implementation(group = "org.scalafx", name = "scalafx_${scalaMajorVersion}", version = scalaFxVersion) //'8.0.144-R12'
    //  implementation(group = "org.scalafx", name = "scalafxml-core-sfx8_${scalaMajorVersion}", version = scalaFXMLVersion) //'0.4'
//    implementation(group = "", name = "_${scalaMajorVersion}")
    scalaFXModules(this)
    javaOpenFXModules(this)
    //  scalaCompiler "org.scalamacros:paradise_2.12.6:2.1.1"


}


application {
    mainClass.set("io.truthencode.ddo.ui.poc.ScalaFXHelloWorld")
}

javafx {
    this.version = "11"

}

tasks.withType<ScalaCompile>().configureEach {
    sourceCompatibility = sc
    targetCompatibility = tc
    scalaCompileOptions.apply {

        additionalParameters?.plusAssign(listOf("-feature", "-deprecation", "-Ywarn-dead-code", "-Ylog-classpath"))
        // Need to add -Ypartial-unification for Tapir
    }
}

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.ALL
    gradleVersion = "6.6.1"

}

//application {
//    mainModule.set("io.truthencode.ddo.ui.poc") // name defined in module-info.java
//    mainClass.set("io.truthencode.ddo.ui.poc.ScalaFXHelloWorld")
//}


//def String scalaCompilerOptions="-Xplugin:$configurations.scalaCompiler.singleFile.path"
//compileScala.scalaCompileOptions.additionalParameters = [scalaCompilerOptions]
//compileTestScala.scalaCompileOptions.additionalParameters = [scalaCompilerOptions]

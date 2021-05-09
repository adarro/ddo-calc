package io.truthencode.gradle.kotlin.dsl

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.UnknownDomainObjectException
import org.gradle.api.tasks.SourceSet
import org.gradle.kotlin.dsl.NamedDomainObjectContainerScope
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*



//fun NamedDomainObjectContainerScope<SourceSet>.getOrCreate(
//    name: String,
//    configureAction: Action<in SourceSet>
//): SourceSet {
//
//    return try {
//        getByName(name)
//    } catch (e: UnknownDomainObjectException) {
//        create(name)
//    }
//}

/**
 * Configures the current project as a Kotlin project by adding the Kotlin `stdlib` as a dependency.
 */
fun Project.scalaProject() {
    logger.info("TESTING ERROR POST #################################################")
    try {
        val cls : Class<Plugin<*>> = Class.forName("com.github.maiflai.ScalaTestPlugin") as Class<Plugin<*>>
        apply {
            plugin(cls)
        }
    }
    catch (e:Throwable) {
        logger.info("********************************************** Icky Icky")
        logger.error(e.message,e)
    }

    this.apply {
        plugin("scala")
        plugin("java-library") // cross-compiler is incompatible with java-library ATM
// Attempting to include 3rd party plugins
// plugin by ID fails
      //  plugin("com.github.maiflai.scalatest")// version "0.25"
      // attempt by implementation class as string fails
     // plugin(com.github.maiflai.ScalaTestPlugin)

   
   // remove other 3rd party until work around is found
   //     plugin("org.scoverage")// version "3.1.5"
        // IDE Specific
        plugin("idea")
     //   plugin("org.unbroken-dome.test-sets")
    }

//    dependencies {
//        "compile"(kotlin("stdlib"))
//    }
}
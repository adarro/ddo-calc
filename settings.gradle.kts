rootProject.name = "ddo-calc-parent"
// include ':ddo-plot'
include(":ddo-core")
// include ':ddo-etl'
// include ':ddo-data:ddo-data-mongo'
// include ':ddo-data'
// include ':ddo-route'

// requires updated R language gradle plugin.
// project(':ddo-plot').projectDir = "$rootDir/ddo-plot" as File
project(":ddo-core").projectDir = File("$rootDir/ddo-core")
// project(':ddo-web').projectDir = "$rootDir/ddo-web" as File
//project(':ddo-etl').projectDir = "$rootDir/ddo-etl" as File
//project(':ddo-data:ddo-data-mongo').projectDir = "$rootDir/ddo-data/ddo-data-mongo" as File
//project(':ddo-data').projectDir = "$rootDir/ddo-data" as File
// project(':ddo-route').projectDir = "$rootDir/ddo-route" as File


// gradle.experimentalFeatures.enable()
//include 'ddo-odata'
//include 'ddo-odata-server'
val scoveragePluginVersion: String by settings
val versionsPluginVersion: String by settings
val buildScanPluginVersion: String by settings
val useLatestVersionsPluginVersion: String by settings
val testSetsPluginVersion: String by settings
val versionEyePluginVersion: String by settings

pluginManagement {
    plugins {
        id("org.unbroken-dome.test-sets") version testSetsPluginVersion
        id("org.scoverage") version scoveragePluginVersion
        //   id "findbugs"
        //  id "org.standardout.versioneye" version versionEyePluginVersion
        id("com.github.ben-manes.versions") version versionsPluginVersion
        id("se.patrikerdes.use-latest-versions") version useLatestVersionsPluginVersion
        id("com.gradle.build-scan") version buildScanPluginVersion
    }
}



rootProject.name = "ddo-avro-generator"

pluginManagement {
    //  Scala
    // Coverage
//    val scoveragePluginVersion: String by settings
    // Avro
    val avroHuggerPluginVersion: String by settings
//    val openApiGeneratorPluginVersion: String by settings

//    val kordampGradlePluginVersion: String by settings
//    val semVerPluginVersion: String by settings
    val mooltiverseNyxPluginVersion: String by settings
    val foojayResolverPluginVersionversion: String by settings

    plugins {
//        id("com.github.hierynomus.license") version "0.16.1"
        id("com.zlad.gradle.avrohugger") version avroHuggerPluginVersion
        // id("com.chudsaviet.gradle.avrohugger") version avroHuggerPluginVersion
//        id("org.openapi.generator") version openApiGeneratorPluginVersion
//        id("org.scoverage") version scoveragePluginVersion
//        id("com.mooltiverse.oss.nyx") version mooltiverseNyxPluginVersion
        id("org.gradle.toolchains.foojay-resolver-convention") version foojayResolverPluginVersionversion

//        id("org.kordamp.gradle.project") version kordampGradlePluginVersion
//        id("net.thauvin.erik.gradle.semver") version semVerPluginVersion
//        id("ru.vyarus.mkdocs") version "3.0.0"
    }

    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
//    id("com.mooltiverse.oss.nyx")
    id("org.gradle.toolchains.foojay-resolver-convention")
}

dependencyResolutionManagement {
    versionCatalogs {
        // declares an additional catalog, named 'testLibs', from the 'test-libs.versions.toml' file
        create("libs") {
            from(files("../../gradle/libs.versions.toml"))
        }
    }
}
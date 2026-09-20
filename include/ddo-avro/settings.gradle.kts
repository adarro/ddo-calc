
rootProject.name = "ddo-avro-generator"

pluginManagement {
    //  Scala
    // Coverage
//    val scoveragePluginVersion= project.property("") as String
    // Avro
    val avroHuggerPluginVersion = providers.gradleProperty("avroHuggerPluginVersion")
//    val openApiGeneratorPluginVersion= project.property("") as String

//    val kordampGradlePluginVersion= project.property("") as String
//    val semVerPluginVersion= project.property("") as String
    val mooltiverseNyxPluginVersion = providers.gradleProperty("mooltiverseNyxPluginVersion")
    val foojayResolverPluginVersionversion = providers.gradleProperty("foojayResolverPluginVersionversion")

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

plugins {
//    kotlin("jvm") version "1.9.10"
    id("io.truthencode.poc.kantlr.kotlin-library-conventions")
    antlr
//    id("java-library-conventions")
//    id("be.vbgn.ci-detect")
    id("code-quality")
    // id("djaxonomy.test-conventions")
    idea
    id("djaxonomy.kotlin-test-conventions")
}

dependencies {
    implementation(enforcedPlatform(project(":ddo-platform")))
    antlr(libs.antlr4) // use ANTLR version 4
    implementation(libs.logback.classic)
}

repositories {
    mavenCentral()
}

val antlrJavaPath =
    PackagePath(project.layout.buildDirectory.dir("generated-src/java").get().asFile.path, "io.truthencode.ddo.grammar.antlr")

data class PackagePath(val source: String, val packageName: String) {
    /**
     * Property stores the base output folder.
     * This should generally be similar to 'src/generated/java'
     */
    val baseOutputFolder: File = File(source)

    fun packageToPath(packageId: String): File {
        val ps = System.getProperty("file.separator")
        val np = packageId.replace(".", ps)
        logger.warn("baseOutputFolder: $baseOutputFolder")
        return File(baseOutputFolder, np)
    }

    val packageFolder: File
        get() = packageToPath(packageName)
}

tasks {
    generateGrammarSource {
        maxHeapSize = "64m"
        arguments = arguments + listOf("-visitor", "-long-messages")
    }

    named("compileKotlin") {
        dependsOn(generateGrammarSource)
    }

    named("compileTestKotlin") {
        dependsOn(generateTestGrammarSource)
    }
}
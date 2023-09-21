plugins {
    id("djaxonomy.kotlin-library-conventions")
    antlr
    id("code-quality")
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

description = "Antlr Parsing utilities"

val antlrJavaPath =
    PackagePath(
        project.layout.buildDirectory.dir("generated-src/java").get().asFile.path,
        "io.truthencode.ddo.grammar.antlr"
    )

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
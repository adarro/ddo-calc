plugins {
    kotlin("jvm") version "1.7.10"
    antlr
    id("java-library-conventions")
    id("be.vbgn.ci-detect")
    id("code-quality")
    idea
}

dependencies {

    testImplementation(enforcedPlatform("org.junit:junit-bom:5.8.2"))
    // antlr("org.antlr:antlr:3.5.2")   // use ANTLR version 3
    antlr("org.antlr:antlr4:4.10.1") // use ANTLR version 4
    implementation("ch.qos.logback:logback-core:1.2.11")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("org.slf4j:slf4j-api:1.7.36")
    // JUnit 5
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api")
    testImplementation(group = "org.junit.platform", name = "junit-platform-runner")
    testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-engine")
    testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-launcher")
//    testRuntimeOnly(group = "co.helmethair", name = "scalatest-junit-runner")
    testRuntimeOnly(group = "org.junit.vintage", name = "junit-vintage-engine")
    testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine")

}
repositories {
    mavenCentral()
}
//val javaGenPath = File("src/generated/java")
val antlrJavaPath = PackagePath("src/generated/java","io.truthencode.ddo.grammer.antlr")
data class PackagePath(val source: String, val packageName: String) {
    /**
     * Property stores the base output folder.
     * This should generally be similar to 'src/generated/java'
     */
    val baseOutputFolder: File = File(source)
    fun packageToPath(packageId: String): File {
        val ps = System.getProperty("file.separator")
        val np = packageId.replace(".", ps)
        return File(baseOutputFolder, np)
    }

    val packageFolder: File
        get() = packageToPath(packageName)

}
// src/generated/java/EnchantmentsParserVisitor.java
sourceSets {
    named("main") {
        java.sourceDirectories.files.plus(antlrJavaPath.baseOutputFolder)

    }
}

// Intellij
idea {
    module {
        sourceDirs.add(antlrJavaPath.baseOutputFolder)
        generatedSourceDirs.add(antlrJavaPath.baseOutputFolder)
        // .addAll(listOf(javaGenPath))
    }
}


kotlin {
    this.sourceSets.named("main") {
        kotlin.srcDir(antlrJavaPath.baseOutputFolder)
        this.kotlin.sourceDirectories.plus(antlrJavaPath.baseOutputFolder)
    }
}


tasks {
    generateGrammarSource {
        maxHeapSize = "64m"
        arguments = arguments + listOf("-visitor", "-long-messages", "-package", "io.truthencode.ddo.grammer.antlr")
        outputDirectory = antlrJavaPath.packageFolder

    }

    withType(Test::class.java) {
        systemProperties["concordion.output.dir"] = "${reporting.baseDir}/spec"
    }
    logger.info("concordion output directory: *************************************")
    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform {
            includeEngines = setOf("junit-jupiter", "vintage")
            if (ci.isCi)
                excludeTags = setOf(
                    "Integration",
                    "io.truthencode.tags.Integration",
                    "FunctionOnly",
                    "io.truthencode.tags.FunctionOnly"
                )
            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        outputs.upToDateWhen { false }
    }
}
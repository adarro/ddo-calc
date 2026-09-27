import java.io.FilenameFilter
import java.nio.file.FileSystems

plugins {
    id("buildlogic.kotlin-library-conventions")
    antlr
    idea
    id("buildlogic.kotlin-test-conventions")
}

dependencies {
    implementation(platform(project(":ddo-platform")))
    antlr(libs.antlr4) // use ANTLR version 4
    implementation(libs.logback.classic)
}

description = "Antlr Parsing utilities"

val antlrJavaPath =
    PackagePath(
        project.layout.buildDirectory
            .dir("generated-src/java")
            .get()
            .asFile.path,
        "io.truthencode.ddo.grammar.antlr",
    )

data class PackagePath(
    val source: String,
    val packageName: String,
) {
    /**
     * Property stores the base output folder.
     * This should generally be similar to 'src/generated/java'
     */
    val baseOutputFolder: File = File(source)

    private fun namespaceToPath(packageId: String = packageName): String {
        val ps = FileSystems.getDefault().separator
        return packageId.replace(".", ps)
    }

    fun packageToPath(
        packageId: String = packageName,
        outputFolderBase: File = baseOutputFolder,
    ): File {
        val np = namespaceToPath(packageId)
        logger.warn("baseOutputFolder: $outputFolderBase")
        return File(outputFolderBase, np)
    }

    val packageFolder: File
        get() = packageToPath(packageName)
}

tasks {     
    generateGrammarSource {
        logger.debug("outputDirectory: ${outputDirectory.path}")
        val outPath = antlrJavaPath.packageToPath(outputFolderBase = outputDirectory).path
        // TODO: Antlr task should avoid doFirst and doLast
        // possibly add dependent copy task to move files to right location
        doFirst {
            logger.debug("making outputDirectory: ${outputDirectory.path}")
            File(outPath).mkdirs()
        }
        logger.debug("setting -lib to : $outPath")
        maxHeapSize = "64m"
        arguments = arguments +
            listOf(
                "-visitor",
                "-long-messages",
            )

//        this.includes.clear()
//        this.includes.addAll(listOf("**/*Lexer.g4"))
        doLast {
            logger.debug("Moving files to right location ..")
            val filter =
                FilenameFilter { _, name ->
                    name.endsWith(".java")
                }
            val companionExtensions = listOf("tokens", "interp")
            val move = mutableMapOf<File, File>()
            outputDirectory.listFiles(filter)?.forEach {
                // This captures the package name from the file.
                // We also need to move companion files (.tokens and .interp)

                logger.debug("Queueing ${it.name} to right location ..")
                val pkg = it.readLines().find { it.trim().startsWith("package") }
                pkg?.let { fi ->
                    val packagePath = fi.split(' ')[1].removeSuffix(";").replace('.', '/')
                    move[it] = File("${outputDirectory.path}/$packagePath/${it.name}")
                    companionExtensions.forEach { ext ->
                        val exFileName = it.name.removeSuffix(".${it.extension}").plus(".$ext")
                        logger.debug("checking for companion file $exFileName")
                        val exFile = File(it.parentFile, exFileName)
                        if (exFile.exists()) {
                            logger.debug("companion file {} exists .. queueing for move", exFile)
                            move[exFile] = File("${outputDirectory.path}/$packagePath/${exFile.name}")
                        }
                    }
                }
            }
            move.forEach { (k, v) ->
                if (k.exists()) {
                    logger.debug("{} is in the wrong location .. {}", k, k.path)
                    if (!v.exists()) {

                        val result = k.renameTo(v)
                        val rMsg = if (result) "succeeded" else "failed"
                        logger.debug("moving to ${v.path} $rMsg")
                    } else {
                        logger.debug("{} already exists .. deleting {}.path", v.path, k)
                        val result = k.delete()
                        val rMsg = if (result) "succeeded" else "failed"
                        logger.debug("deleting {}.path {}", k, rMsg)
                    }
                }
            }
        }
    }

    named("compileKotlin") {
        dependsOn(generateGrammarSource)
    }

    named("compileTestKotlin") {
        dependsOn(generateTestGrammarSource)
    }

    fun extractPackageNameFromGrammarFile(grammarFile: File): String {
        var grammarPackage = "unknown.package"
        val packageRegex = Regex("""[ ]*package[ ]*([a-zA-Z]+[a-zA-Z0-9.-_]*)[ ]*;""")
        grammarFile.forEachLine { line ->
            packageRegex.find(line)?.let { matchResult ->
                grammarPackage = matchResult.groupValues[1]
            }
        }
        return grammarPackage
    }

    fun moveAntlrGeneratedFilesToTheirPackages(
        grammarFiles: FileTree,
        generatedFolder: String,
        destFolder: String,
    ) {
        grammarFiles.forEach { file ->
            val grammarName =
                if (file.name.lastIndexOf('.') >= 0) {
                    file.name.substring(
                        0,
                        file.name.lastIndexOf('.'),
                    )
                } else {
                    file.name
                }
            val grammarPackage = extractPackageNameFromGrammarFile(file)
            copy {
                from(generatedFolder)
                include("$grammarName*.*")
                into("$destFolder/${grammarPackage.replace(".", "/")}")
            }
        }
        project.delete(fileTree(generatedFolder).include("*.*"))
    }
}

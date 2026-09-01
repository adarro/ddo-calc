package io.truthencode.buildlogic

import org.gradle.api.artifacts.transform.InputArtifact
import org.gradle.api.artifacts.transform.TransformAction
import org.gradle.api.artifacts.transform.TransformOutputs
import org.gradle.api.artifacts.transform.TransformParameters
import org.gradle.api.file.FileSystemLocation
import org.gradle.api.logging.Logging
import org.gradle.api.provider.Provider
import java.io.File

/**
 * Kludge is a temporary workaround for a problem that should be fixed in the future.
 */

// 1. Register a custom rule that catches the missing classifier error
abstract class JLineClassifierFixAction : TransformAction<TransformParameters.None> {
    // Initialize the Gradle logger for this class
    private val logger = Logging.getLogger(JLineClassifierFixAction::class.java)

    @get:InputArtifact
    abstract val inputArtifact: Provider<FileSystemLocation>

    override fun transform(outputs: TransformOutputs) {
        val file = inputArtifact.get().asFile
        logger.warn("evaluating $file")
        if (file.name.contains("jline")) {
            logger.warn("Located candidate $file")

            // If Quarkus forced a jdk8 classifier layout on the file name internally
            if (file.name.contains("-jdk8")) {
                logger.warn("Transforming $file")
                // Quietly resolve it back to the normal artifact cache item
                val correctedFile = File(file.parentFile, file.name.replace("-jdk8", ""))
                if (correctedFile.exists()) {
                    outputs.file(correctedFile)
                    return
                }
            }
        }
        outputs.file(file)
    }
}

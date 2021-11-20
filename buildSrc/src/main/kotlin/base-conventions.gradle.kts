plugins {
    id("com.diffplug.spotless")
}

enum class ScriptLanguage { GradleBuild, KotlinScriptBuild }

fun buildLang(): ScriptLanguage {
    return if (project.buildFile.name.endsWith("kts")) ScriptLanguage.KotlinScriptBuild else ScriptLanguage.KotlinScriptBuild
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    if (buildLang() == ScriptLanguage.KotlinScriptBuild) {
        logger.quiet("SPOTLESS: applying / configuring kotlin script formatting to ${project.name}")
        kotlinGradle {
            diktat("1.0.0-rc.4")
        }
    }
    project.plugins.withId("scala") {
        logger.quiet("SPOTLESS: applying / configuring scala formatting to ${project.name}")
        scala {
            val scalaFmtVersion: String by project
            // version and configFile are both optional
            scalafmt(scalaFmtVersion).configFile(rootProject.file(".scalafmt.conf"))
        }
    }
}


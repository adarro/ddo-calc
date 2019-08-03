import io.truthencode.gradle.kotlin.dsl.getOrCreate
plugins {
    `java-library`
    scala
//    id "com.diffplug.gradle.spotless" version "3.10.0"
    //  id "com.github.maiflai.scalatest" version "0.25"
    id("org.scoverage") // version "3.1.5"
    //   id 'nebula.optional-base' version '3.0.3'
    //   id "org.junit.platform.gradle.plugin"

}

// configure scalac
tasks.withType(ScalaCompile::class) {
    this.scalaCompileOptions.apply {
        encoding = "UTF-8"

        additionalParameters = listOf("-feature", "-Ywarn-unused", "-Xlint", "-Xcheckinit", "-Yrangepos", "deprecation")
        forkOptions.apply {
            memoryMaximumSize = "1g"
        }
        // daemonServer = "localhost:4243"
    }
}
configure<JavaPluginConvention> {
    sourceSets {

        getOrCreate("main") {
            withConvention(ScalaSourceSet::class) {
                scala.srcDir("src/main/scala")
            }
        }

        getOrCreate("test") {
            withConvention(ScalaSourceSet::class) {
                scala {
                    srcDir { "src/test/scala" }
                    exclude(
                        "**/*Spec.scala",
                        "**/*Helper*",
                        "**/*Builder*"
                    )
//            include {'**/*Test.scala'}
                }

            }
        }

        getOrCreate("acceptanceTest") {

            resources {
                srcDirs("src/specs/resources", "src/test/specs")
            }

            withConvention(ScalaSourceSet::class) {
                scala {
                    srcDir("src/specs/scala")
                }

                scala.srcDir("src/specs/scala")
            }

            compileClasspath += sourceSets["main"].runtimeClasspath
            compileClasspath += sourceSets["test"].runtimeClasspath
        }

    }
}

task<Test>("acceptanceTest") {
    group = "verification"
    description = "Runs Acceptance Tests"
//    testClassesDirs = project.sourceSets["acceptanceTest"].output.classesDirs
//    classpath += sourceSets["acceptanceTest"].runtimeClasspath
    mustRunAfter(getTasksByName("test", recursive = false))
    testLogging {
        showStandardStreams = true
    }

 //   systemProperties["concordion.output.dir"] = "${project.repo}reporting.baseDir/spec"
}



task<Test>("allTests") {
    description = "Runs All Unit and Acceptance Tests"
    dependsOn(getTasksByName("test", false), getTasksByName("acceptanceTest", false))
    group = "verification"
}

tasks.getByName("check").dependsOn(tasks.getByName("allTests"))

dependencies {
    val implementation by configurations.getting
    implementation(group = "org.scala-lang", name = "scala-library", version = "2.12.3")
}
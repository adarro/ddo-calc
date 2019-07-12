// import org.gradle.api.internal.java.JavaLibrary
// import org.gradle.kotlin.dsl.*
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    repositories {
        jcenter()
    }
}

plugins {
    //  scala apply(false)
//    `java-library`
    //  application
    // id("scala") apply (false)
    id("java-library")
    //   id "findbugs"
    //  id "org.standardout.versioneye" version "1.5.0"
    id("com.github.ben-manes.versions") version "0.21.0"
    id("se.patrikerdes.use-latest-versions") version "0.2.9"
    `project-report`

}
//


allprojects {


    //   plugins { `java-library` }
    repositories {
        jcenter()
        mavenCentral()
    }
}
val t = this

subprojects {

this.pluginManager.apply("java-library")
    this.pluginManager.apply("project-report")




//    val p = this
//    p.afterEvaluate {
//        this.withConvention(JavaPluginConvention::class, {
//            sourceCompatibility = JavaVersion.VERSION_1_8
//            targetCompatibility = JavaVersion.VERSION_1_8
//        })
//    }
//    p.afterEvaluate {
//        apply {
//            java {
//                sourceCompatibility = JavaVersion.VERSION_1_8
//                targetCompatibility = JavaVersion.VERSION_1_8
//            }
//        }
//    }

//    plugins.withId("java-library") {
//
//        p.configure(JavaPluginConvention::class.java, {
//            sourceCompatibility = JavaVersion.VERSION_1_8
//            targetCompatibility = JavaVersion.VERSION_1_8
//        })
//    }
    // apply plugin: 'java-library'

    tasks.withType(ScalaCompile::class.java) {
        options.encoding = "UTF-8"
        scalaCompileOptions.encoding = "UTF-8"
        // scalaCompileOptions.deprecation = true
        scalaCompileOptions.additionalParameters =
            listOf("-deprecation", "-feature", "-Ywarn-unused", "-Xlint", "-Xcheckinit", "-Yrangepos")
        // optionally specify host and port of the daemon:
        // scalaCompileOptions.daemonServer = "localhost:4243"
    }
    tasks.create("showPlugins") {
        description = "Lists pluginAware plugins for each project"
        println("Plugins for $project.name")
        project.plugins.forEach {
            println(it)
        }
    }
    tasks.withType<Test> {
        reports.html.isEnabled = false
    }


    repositories {
        mavenLocal()
        jcenter()
        maven("http://repo.spring.io/libs-release-remote")
        maven("https://oss.sonatype.org/content/repositories/releases")
        maven("http://repo1.maven.org/maven2/")
    }

}

tasks.withType<DependencyUpdatesTask> {
    resolutionStrategy {
        componentSelection {
            all {
                val rejected = listOf("alpha", "beta", "rc", "cr", "m", "preview", "b", "ea").any { qualifier ->
                    candidate.version.matches(Regex("(?i).*[.-]$qualifier[.\\d-+]*"))
                }
                if (rejected) {
                    reject("Release candidate")
                }
            }
        }
    }
    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = "json"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}

projectReports {
    this.projects.addAll( t.allprojects)
}
//
//task testReport(type: TestReport) {
//    destinationDir = file("$buildDir/reports/allTests")
//    // Include the results from the `test` task in all subprojects
//    reportOn subprojects*.test
//}
//
//dependencies {
//    compile group: 'org.scala-lang', name: 'scala-library', version: '2.13.0-M5-1775dba'
//}

/*versioneye {
    includeSubProjects = true
}*/

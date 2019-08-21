/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andre White.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.hierynomus.gradle.license.tasks.LicenseCheck
import org.gradle.api.tasks.testing.logging.TestLogEvent

buildscript {
    repositories {
        jcenter()
    }
}

plugins {

    //  application
    id("java-library")
    id("org.unbroken-dome.test-sets")
    id("org.kordamp.gradle.project")
    id("org.kordamp.gradle.bintray")
    id("org.kordamp.gradle.build-scan")
    id("org.scoverage") version "3.1.5"
    idea
    eclipse
    //   id "findbugs"
    //  id "org.standardout.versioneye" version "1.5.0"
    id("com.github.ben-manes.versions")
    id("se.patrikerdes.use-latest-versions")
    id("com.gradle.build-scan")
    id("quality-checks")
    `project-report`
    `build-dashboard`
    id("gradle.site") version "0.6"
    id("com.dorongold.task-tree")


}

val bintrayUsername: String by project
val bintrayApiKey: String by project
val releaseActive: Boolean? = rootProject.findProperty("release") as Boolean?

config {
    release = if (releaseActive != null) releaseActive!! else false

    info {
        name = "DDO Calculations"
        vendor = "TruthEncode"
        description = "DDO Character Analyzer and Planner"
        inceptionYear = "2015"

        links {
            website = "https://github.com/adarro/ddo-calc"
            issueTracker = "https://github.com/adarro/ddo-calc/issues"
            scm = "https://github.com/adarro/ddo-calc/ddo-calc.git"
        }

        scm {
            url = "https://github.com/adarro/ddo-calc"
            developerConnection = "scm:git:git@github.com:adarro/ddo-calc.git"
            connection = "scm:git:git://github.com/github.com/adarro/ddo-calc.git"
        }

        people {
            person {
                id = "adarro"
                name = "Andre White"
                roles = listOf("developer", "owner")
            }
        }
    }

    licensing {
        licenses {
            license {
                id = "Apache-2.0" //org.kordamp.gradle.plugin.base.model.LicenseId.APACHE_2_0
            }
        }
    }

    bintray {
        credentials {
            username = bintrayUsername // project.bintrayUsername
            password = bintrayApiKey // project.bintrayApiKey
        }
        userOrg = "adarro"
        name = rootProject.name
        githubRepo = "adarro/ddo-calc"
        enabled = true
    }
    bom {
        enabled = true
//        signing {
// * To be done later
//        }
    }
    buildInfo {
        buildBy = "Andre White"
    }
    buildScan {
        enabled = true
    }
}

license {
    header = rootProject.file("buildResources/LICENSE_HEADER")

}

tasks.withType(HtmlDependencyReportTask::class.java) {
    projects = projects
}


allprojects {
    repositories {
        jcenter()
        mavenCentral()
    }
}
val t = this

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
}

tasks.register("showDirs") {
    doLast {
        logger.quiet(rootDir.toPath().relativize((project.properties["reportsDir"] as File).toPath()).toString())
        logger.quiet(rootDir.toPath().relativize((project.properties["testResultsDir"] as File).toPath()).toString())
    }
}

tasks.register<TestReport>("testReport") {
    destinationDir = file("$buildDir/reports/allTests")
    // Include the results from the `test` task in all subprojects
    reportOn(subprojects.map { it.tasks["test"] })
}

tasks.register("aggregateReports") {
    dependsOn("testReport", "aggregateScoverage", "projectReport")
    group = "reporting"
    description = "Aggregates Reports into root directory"

}

tasks.register("showReportTasks ") {
    description = "Displays report task names"
    tasks.filter { t -> t.name.contains("report", true) }.forEach {
        println("ReportTask : ${it.name}\t${it.javaClass.name}")
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events.addAll(listOf(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED))
    }
}

site {
    websiteUrl.set("https://github.com/adarro")
    outputDir.set(file("$rootDir/docs"))
    vcsUrl.set("https://github.com/adarro/ddo-calc.git")
}


tasks.withType(GenerateBuildDashboard::class.java) {
    // this.aggregate()
    reports {
        logger.warn("${enabledReports.size} enabled dashboard reports")
        enabledReports.forEach { (n, r) ->
            logger.warn("Dashboard: $n\t${r.name}\t${r.displayName}\t${r.isEnabled}")
        }

    }
}




subprojects {
    val codacy: Configuration by configurations.creating
    this.pluginManager.apply("java-library")
    this.pluginManager.apply("project-report")

    dependencies {

        // https://mvnrepository.com/artifact/com.codacy/codacy-coverage-reporter

        codacy(group = "com.codacy", name = "codacy-coverage-reporter", version = "6.0.2")

    }

    tasks.create("showPlugins") {
        description = "Lists pluginAware plugins for each project"
        println("Plugins for $project.name")
        project.plugins.forEach {
            println(it)
        }
    }
    tasks.withType<Test> {
        reports.html.isEnabled = true
        useJUnitPlatform()
        testLogging {
            events.addAll(listOf(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED))
        }
        systemProperty("concordion.output.dir", "${project.reporting.baseDir}/spec")
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
                } else {
                    val scalaLibLoc = candidate.module == "scala-library" || candidate.group == "org.scala-lang"
                    if (scalaLibLoc) {
                        reject("${candidate.module}:${candidate.module} is locked at ${candidate.version}")
                    }
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



tasks.withType<LicenseCheck> {
    ignoreFailures = true
    strictCheck = true
    
}
projectReports {
    this.projects.addAll(t.allprojects)
}

/*versioneye {
    includeSubProjects = true
}*/

import java.io.Writer

plugins {
    base
    id("test-report-aggregation")
}

dependencies {
    // projectList().map { it -> testReportAggregation(project("$it")) }

    testReportAggregation(project(":ddo-antlr"))
    testReportAggregation(project(":ddo-core"))
    testReportAggregation(project(":ddo-etl"))
    testReportAggregation(project(":ddo-modeling"))
    testReportAggregation(project(":ddo-platform"))
    testReportAggregation(project(":ddo-platform-kotlin"))
    testReportAggregation(project(":ddo-platform-scala"))
    testReportAggregation(project(":ddo-util"))
    // acceptanceTestReportAggregation(project(":ddo-core"))
}

reporting {
    @Suppress("UnstableApiUsage")
    reports {
        val testAggregateTestReport by creating(AggregateTestReport::class) {
//            testType.set(TestSuiteType.UNIT_TEST)
            testSuiteName.set("acceptance-test")
        }
        // val testAggregateAcceptanceTestReport by creating(AggregateTestReport::class) {
        //     testType.set("acceptance-test")
        // }
    }
}
// sonar {
//
//    isSkipProject = true
// }

/**
Task simply prints out a list of projects to be used in the testReportAggregation
this list isn't immediately available in the configuration phase, so we have to cut and paste
from a manually run listProjects task
 */
tasks.register("listProjects") {
    description = "print out report aggregation projects"
    group = "utility"
    doLast {
        val pl = projectList()
        pl.sort()
        val types = listOf("acceptance", "")
        types.forEach { t ->
            pl.forEach { p ->
                if (t == "") {
                    println("testReportAggregation(project(\"$p\"))")
                } else {
                    println("${t}TestReportAggregation(project(\"$p\"))")
                }
            }
        }
    }
}

description = "Utility class for aggregating Reports"

fun projectList(): MutableList<String> {
    logger.error("projectList called")
    val sb = mutableListOf<String>()
    findProjects(sb, project.rootProject)
    logger.debug("findProject results: {}", sb)
    return sb
}

// create a list of non-test projects
fun findProjects(
    writer: MutableList<String>,
    project: Project,
) {
    if (!project.name
            .contains("test") &&
        project.name.startsWith("ddo") &&
        project != project.rootProject
    ) {
        writer.add(":${project.name}")
        logger.info("found ${project.childProjects.size} child projects in ${project.name}")
    }
    project.childProjects.forEach { (_, childProject) -> findProjects(writer, childProject) }
}

/**
Should be called with mdDocsBuild task

 Transliterated Groovy -> Kotlin from
 https://gist.githubusercontent.com/nikialeksey/7cefae6b3104ce9a2c765197343bc436/raw/fb61c7d35480f9ae16650aefbb31c9c11420bec4/dependency-report.gradle
 Inspired by https://gist.github.com/tzachz/419478fc8b009e953f5e5dc39f3f3a2a
 Task creates a .dot file with all inter-module dependencies
 Supports any depth of nested modules

 */
tasks.register("moduleDependencyReport") {
    description = "Creates a .dot file with all inter-module dependencies"
    group = "documentation"
    doLast {
        val file =
            rootProject.layout.projectDirectory
                .file("docs/developers_guide/project-dependencies.dot")
                .asFile
        file.delete()
        file.bufferedWriter().use { w ->
            val sb = StringBuffer()
            sb.append("strict digraph {\n")
            sb.append("splines=ortho\n")
            w.write(sb.toString())
            printDependencies(w, rootProject)
            w.write("}\n")
        }
    }
//     // val t = this
//     tasks.withType(ru.vyarus.gradle.plugin.mkdocs.task.MkdocsBuildTask::class) {
//         // dependsOn(t)
//         // dependsOn(tasks.named(":ddo-test-results:moduleDependencyReport"))
// dependsOn(tasks.named("moduleDependencyReport"))
//     }
}

// tasks.named("mkDocsBuild") {
//     dependsOn(tasks.named("moduleDependencyReport"))
// }

// recursively print dependencies to file and move on to child projects
fun printDependencies(
    writer: Writer,
    project: Project,
) {
    if (!project.name
            .contains("test")
    ) {
        project.configurations
            .map {
                it.allDependencies.map { d -> d }
            }.flatten()
            .toSet()
            .filterIsInstance(ProjectDependency::class.java)
            .filterNot { it.name.contains("test") || it.name == project.name }
            .map {
                logger.error("Mapping project: $project -> ${it.name}")
                "\"${project.name}\" -> \"${it.name}\"\n"
            }.forEach(writer::write)
        logger.info("found ${project.childProjects.size} child projects in ${project.name}")
    }
    project.childProjects.forEach { (_, childProject) ->
        logger.error("recursive call for $childProject")
        printDependencies(writer, childProject)
    }
}
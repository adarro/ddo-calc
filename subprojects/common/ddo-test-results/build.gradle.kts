import java.io.Writer

plugins {
    base
    id("test-report-aggregation")
}

dependencies {
    testReportAggregation(project(":ddo-core"))
}

reporting {
    reports {
        val testAggregateTestReport by creating(AggregateTestReport::class) {
            testType.set(TestSuiteType.UNIT_TEST)
        }
    }
}

description = "Utility class for aggregating Reports"
// Transliterated Groovy -> Kotlin from
// https://gist.githubusercontent.com/nikialeksey/7cefae6b3104ce9a2c765197343bc436/raw/fb61c7d35480f9ae16650aefbb31c9c11420bec4/dependency-report.gradle
// Inspired by https://gist.github.com/tzachz/419478fc8b009e953f5e5dc39f3f3a2a
// Task creates a .dot file with all inter-module dependencies
// Supports any depth of nested modules

tasks.register("moduleDependencyReport") {
    doLast {
        val file = rootProject.layout.projectDirectory.file("docs/developers_guide/project-dependencies.dot").asFile
        file.delete()
        file.bufferedWriter().use { w ->
            val sb = StringBuffer()
            sb.append("strict digraph {\n")
            sb.append("splines=ortho\n")
            w.write(sb.toString())
            printDeps(w, rootProject)
            w.write("}\n")
        }
    }
}

// recursively print dependencies to file and move on to child projects
fun printDeps(writer: Writer, project: Project) {
    if (!project.name
            .contains("test")
    ) {
        project.configurations.map {
            it.allDependencies.map { d -> d }
        }
            .flatten()
            .toSet()
            .filterIsInstance(ProjectDependency::class.java)
            .filterNot { it.name.contains("test") || it.name == project.name }
            .map { "\"${project.name}\" -> \"${it.name}\"\n" }
            .forEach(writer::write)
        logger.info("found ${project.childProjects.size} child projects in ${project.name}")
    }
    project.childProjects.forEach { (_, childProject) -> printDeps(writer, childProject) }
}
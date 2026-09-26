import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    jacoco
}
/*

Java's (jacoco) and Kotlin at this time.
Scala will utilize scoverage instead.
(Scala's coverage is available in scala-conventions.
NOTICE: You must still include a kotlin app / library etc. profile as we do not auto-include kotlin / java library conventions here.
 */

val libs = the<LibrariesForLibs>()

jacoco {
    // TODO migrate hardcode to Version Catalog (if needed)
    toolVersion =
        libs.versions.jacoco.tools.version
            .get()
//    reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}

tasks.withType(JacocoReport::class) {
    reports {
        xml.required.set(true)
        csv.required.set(false)
//        project.plugins.withId("jvm-test-suite") {
//
//            dependsOn(project.testing.suites)
//        }
//        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

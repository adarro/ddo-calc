plugins {
    jacoco
}
/*

Java's (jacoco) and Kotlin at this time.
Scala will utilize scoverage instead.
(Scala's coverage is available in scala-conventions.
NOTICE: You must still include a kotlin app / library etc. profile as we do not auto-include kotlin / java library conventions here.
 */

jacoco {
    toolVersion = "0.8.12"
//    reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}

tasks.withType(JacocoReport::class) {
    reports {
        xml.required.set(true)
        csv.required.set(false)
//        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}
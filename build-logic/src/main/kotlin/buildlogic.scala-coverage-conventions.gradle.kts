import org.gradle.accessors.dm.LibrariesForLibs

import org.scoverage.ScoverageReport

plugins {
    id("buildlogic.test-conventions")

    id("org.scoverage")
}
val libs = the<LibrariesForLibs>()
val builderScalaVersion: String by project


scoverage {
    scoverageVersion.set(libs.versions.scoverage.engine)
    logger.warn("${project.name} (scoverage) $builderScalaVersion")
    val cfgs =
        mapOf(
            Pair(org.scoverage.CoverageType.Branch, 0.5.toBigDecimal()),
            Pair(org.scoverage.CoverageType.Statement, 0.75.toBigDecimal()),
        ).map { p ->
            val cfg = org.scoverage.ScoverageExtension.CheckConfig()
            cfg.setProperty("coverageType", p.key)
            cfg.setProperty("minimumRate", p.value)
            cfg
        }
    checks.plusAssign(cfgs)

}

tasks.register<org.scoverage.ScoverageAggregate>("aggregateScoverage") {
    group = "verification"
    description = "Aggregates the scoverage reports"
    coverageDebug = false
    coverageOutputCobertura = true
    coverageOutputXML = true
    coverageOutputHTML = true
    deleteReportsOnAggregation = false
    reportDir = file("${layout.buildDirectory}/reports/scoverage")
    sourceEncoding = "UTF-8"
    sources = project.sourceSets.main.get().allSource
    runner = tasks.withType<ScoverageReport>().first().runner
    val dirs = dirsToAggregateFrom.get()
//    dirsToAggregateFrom.get().add(project.layout.buildDirectory.get().dir("reports/scoverage").asFile)
    logger.error("scoverage agg directories ${dirs.size}")
    dirs.forEach { d ->
        logger.error(d.name)
    }


}

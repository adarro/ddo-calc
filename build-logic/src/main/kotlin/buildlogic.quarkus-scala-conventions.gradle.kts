import org.gradle.kotlin.dsl.dependencies

plugins {

    id("buildlogic.bloop")
    id("buildlogic.quarkus-common-conventions") apply false
}

// https://github.com/scalacenter/bloop/pull/1548
bloop {
    stdLibName = "scala3-library_3"
}

// currently only supporting scala 3x here
val versions =
    mapOf(
        "SCALA3" to "3.5.0",
        "QUARKUS_SCALA3" to "1.0.0",
    )

dependencies {
    implementation("org.scala-lang:scala3-compiler_3:${versions["SCALA3"]}")
    implementation("io.quarkiverse.scala:quarkus-scala3:${versions["QUARKUS_SCALA3"]}")
}
plugins {
    // TODO: fix apply false plugin deprecation
    id("buildlogic.quarkus-common-conventions") apply false
}

dependencies {
    implementation("io.quarkus:quarkus-kotlin")
}

plugins {
    id("buildlogic.quarkus-common-conventions")
    id("djaxonomy.common-conventions")
}

description = "Athenaeum Codex"

dependencies {
    // Front-end
    implementation(libs.webjars.bootstrap)
    implementation(libs.bootstrap.icons)
    // https://mvnrepository.com/artifact/org.webjars/jquery
    implementation(libs.webjars.jquery)

    implementation(libs.quarkus.webjars)

    implementation(libs.quarkus.renarde)
    implementation(libs.quarkus.renarde.backoffice)
    implementation(libs.quarkus.renarde.security)

    implementation(libs.quarkus.mailer)

    // Database
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation(libs.quarkus.flyway)

    testImplementation(libs.quarkus.renarde.tests)
    // reactive client
//    implementation("io.quarkus:quarkus-hibernate-reactive-rest-data-panache")
//    implementation("io.quarkus:quarkus-reactive-pg-client")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}
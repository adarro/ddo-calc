import org.gradle.jvm.tasks.Jar

plugins {
    id("djaxonomy.kotlin-library-conventions")
    id("buildlogic.quarkus-common-conventions")
    id("buildlogic.quarkus-kotlin-conventions")
}

description = "ReSTFull Data Access Entities"

dependencies {
    implementation("io.quarkus:quarkus-smallrye-openapi")
    //    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation("io.quarkus:quarkus-hibernate-reactive-rest-data-panache")
    implementation("io.quarkus:quarkus-reactive-pg-client")
//    implementation("io.quarkus:quarkus-rest") included by quarkus-rest-jackson
    implementation("io.quarkus:quarkus-rest-jackson")
    implementation(libs.quarkus.hibernate.orm.panache)
    implementation(libs.quarkus.hibernate.orm.panache.kotlin)
//    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    //  lightweight alternative to hibernate-reactive-panache
    // Hibernate JPA assistance
    implementation(libs.hypersistence.utils)
//    implementation(libs.jetbrains.xodus.xodus.openAPI)
    implementation(libs.xodus.openAPI)
    testImplementation(libs.testing.assertj)
    testImplementation("io.rest-assured:rest-assured")
}

tasks.withType(Jar::class) {
    this.duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
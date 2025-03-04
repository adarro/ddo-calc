import org.gradle.jvm.tasks.Jar

plugins {
    id("buildlogic.kotlin-library-conventions")
    id("buildlogic.quarkus-common-conventions")
    id("buildlogic.quarkus-kotlin-conventions")
    id("code-quality")
}

description = "ReSTFull Data Access Entities"

dependencies {
    implementation(libs.quarkus.smallrye.openapi)
    implementation(libs.quarkus.jdbc.postgresql)
    implementation(libs.quarkus.hibernate.reactive.rest.data.panache)
    implementation(libs.quarkus.reactive.pg.client)
    implementation(libs.quarkus.rest) // included by quarkus-rest-jackson
    implementation(libs.quarkus.rest.jackson)
    implementation(libs.quarkus.hibernate.orm.panache)
    implementation(libs.quarkus.hibernate.orm.panache.kotlin)
//    implementation(libs.quarkus.resteasy.reactive.jackson)
    //  lightweight alternative to hibernate-reactive-panache
    // Hibernate JPA assistance
    implementation(libs.hypersistence.utils)
//    implementation(libs.jetbrains.xodus.xodus.openAPI)
    implementation(libs.xodus.openAPI)
    testImplementation(libs.testing.assertj)
    testImplementation(libs.rest.assured)
}

tasks.withType(Jar::class) {
    this.duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
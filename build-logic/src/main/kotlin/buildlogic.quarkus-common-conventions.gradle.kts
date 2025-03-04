/**
 * This file is part of the common build-logic project.
 * Adds quarkus specific core plugins and configurations
 * Includes basic CDI, Health Checks, Validation (Jakarta ala Hibernate) and basic testing.
 *
 * Advanced features (e.g. OpenAPI, RESTEasy Reactive, etc.) should be added to the project-specific build logic
 *
 */
plugins {
    id("io.quarkus")
    id("buildlogic.common-conventions")
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("$quarkusPlatformGroupId:$quarkusPlatformArtifactId:$quarkusPlatformVersion"))
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-smallrye-health")
    implementation("io.quarkus:quarkus-hibernate-validator") {
        because(
            "Hibernate Validator is an implementation of the Jakarta Bean Validation specification." +
                " Not specific to JPA.",
        )
    }
//    implementation("io.quarkus:quarkus-rest")
    // basic Quarkus Unit, Component and Integration test support included.
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.quarkus:quarkus-junit5-component")
//    testImplementation("io.rest-assured:rest-assured")
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
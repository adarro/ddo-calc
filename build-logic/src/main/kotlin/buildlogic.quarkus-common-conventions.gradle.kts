import org.gradle.accessors.dm.LibrariesForLibs

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

val libs = the<LibrariesForLibs>()

dependencies {

    // TODO: add an extension property to allow toggling enforced verses standard platform dependency
    //   relegated to platform from enforcedPlatform
    implementation(platform(libs.quarkus.platform.bom))
    implementation(libs.quarkus.arc)
    implementation(libs.quarkus.smallrye.health)
    implementation(libs.quarkus.hibernate.validator) {
        because(
            "Hibernate Validator is an implementation of the Jakarta Bean Validation specification." +
                " Not specific to JPA.",
        )
    }

    // basic Quarkus Unit, Component and Integration test support included.
    // TODO: move test dependencies to test-conventions
    testImplementation(libs.quarkus.junit5)
    testImplementation(libs.quarkus.junit5.component)
//    testImplementation("io.rest-assured:rest-assured")
// FIXME: if we auto include this it should be pushed into the test-conventions but it is needed here if we want to flag Quarkus tests
//    testImplementation(project(":ddo-testing-util"))
}

tasks {
    withType<Test> {
        systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
        // See FIXME comment above for context on test tagging
        // includeTags("io.quarkus.test.junit.QuarkusTest", "Unit")
    }
}

// configurations.all {
//    if (name.contains("quarkus")) {
//        val cfgName = name
//        resolutionStrategy.dependencySubstitution {
//            // Intercept any attempt to locate a jdk8 classified version of JLine
//            // substitute(module("org.jline:jline:4.2.1"))
//            //     .using(module("org.jline:jline:4.2.1"))
//            //     .withoutClassifier()
//            // catch any JLine dependencies that might be requested with a jdk8 classifier
//            all {
//                val requested = this.requested
//                if (requested is ModuleComponentSelector && requested.group == "org.jline") {
//                    logger.warn("$cfgName Substituting JLine dependency: ${requested.group}:${requested.module}:${requested.version}")
//                    this.useTarget("${requested.group}:${requested.module}:${requested.version}")
//                }
//            }
//        }
//    }
// }

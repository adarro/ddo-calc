plugins {
    id("io.quarkus")
    id("djaxonomy.common-conventions")
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("$quarkusPlatformGroupId:$quarkusPlatformArtifactId:$quarkusPlatformVersion"))
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-smallrye-health")
    implementation("io.quarkus:quarkus-hibernate-validator") {
        because("Hibernate Validator is an implementation of the Jakarta Bean Validation specification. Not specific to JPA.")
    }
//    implementation("io.quarkus:quarkus-rest")
    testImplementation("io.quarkus:quarkus-junit5")
//    testImplementation("io.rest-assured:rest-assured")
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}

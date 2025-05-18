import io.freefair.gradle.plugins.lombok.tasks.Delombok
import org.gradle.jvm.tasks.Jar

plugins {
    id("buildlogic.java-library-conventions")
    id("buildlogic.java-coverage-conventions")
    id("buildlogic.test-conventions")
//    id("buildlogic.kotlin-library-conventions")
    id("buildlogic.quarkus-common-conventions")
//    id("buildlogic.quarkus-kotlin-conventions")
    id("code-quality")
    alias(libs.plugins.lombok)
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
//    implementation(libs.quarkus.hibernate.orm.panache.kotlin)

//    implementation(libs.quarkus.resteasy.reactive.jackson)
    //  lightweight alternative to hibernate-reactive-panache
    // Hibernate JPA assistance
    implementation(libs.hypersistence.utils)
//    implementation(libs.jetbrains.xodus.xodus.openAPI)
//    implementation(libs.xodus.openAPI)
    testImplementation(libs.testing.assertj)
    testImplementation(libs.rest.assured)
}
val outputDir by extra { file("${layout.buildDirectory}/delombok") }
tasks {

    withType(Jar::class) {
        this.duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }



//    val delombok by registering(Delombok::class) {
//        dependsOn(compileJava)
//        val outputDir by extra { file("${layout.buildDirectory}/delombok") }
//        outputs.dir(outputDir)
//        sourceSets["main"].java.srcDirs.forEach {
//            inputs.dir(it)
//            this.actions.add{
//
//            }
//          //  args(it, "-d", outputDir)
//        }
//
//        doFirst {
//            outputDir.delete()
//        }
//    }

    javadoc {
        dependsOn(delombok)
//        val outputDir: File by delombok.get().extra
        source = fileTree(outputDir)
        isFailOnError = false
    }


}

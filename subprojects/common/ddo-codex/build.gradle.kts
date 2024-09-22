plugins {
    id("buildlogic.quarkus-common-conventions")
    id("djaxonomy.common-conventions")
}

description = "Athenaeum Codex"

dependencies {
    implementation(project(":ddo-dal"))
    // Front-end
    implementation(libs.webjars.bootstrap)
    implementation(libs.bootstrap.icons)
    // https://mvnrepository.com/artifact/org.webjars/jquery
    implementation(libs.webjars.jquery)

    implementation(libs.quarkus.webjars)

    implementation(libs.quarkus.renarde)
    implementation(libs.quarkus.renarde.backoffice)
    implementation(libs.quarkus.renarde.security)
//    implementation("io.quarkus:quarkus-hibernate-reactive-rest-data-panache")
//    implementation("io.quarkus:quarkus-reactive-pg-client")
    implementation(libs.quarkus.mailer)

    // Database
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation(libs.quarkus.flyway)

//    // Extension methods for Java

    implementation(libs.systems.manifold.ext.rt)
    annotationProcessor(libs.systems.manifold.ext)
    testAnnotationProcessor(libs.systems.manifold.ext)

    testImplementation(libs.quarkus.renarde.tests)
    // reactive client
//    implementation("io.quarkus:quarkus-hibernate-reactive-rest-data-panache")
//    implementation("io.quarkus:quarkus-reactive-pg-client")
}

fun isModuleAware(): Boolean {
    when (JavaVersion.current()) {
        JavaVersion.VERSION_1_8 -> return false
        else -> {
            var rVal = false
            sourceSets.main {
                rVal = this.allJava.files.any { it.name == "module-info.java" }
            }
            return rVal
        }
    }
}

tasks.withType<JavaCompile> {
    val args = listOf("-parameters")

    if (isModuleAware()) {
        options.compilerArgs.addAll(
            args.plus(
                listOf(
                    "-Xplugin:Manifold",
                    "--module-path",
                    this.classpath.asPath,
                ),
            ),
        )
    } else {
        options.compilerArgs.addAll(args.plus("-Xplugin:Manifold"))
    }
    options.encoding = "UTF-8"
}
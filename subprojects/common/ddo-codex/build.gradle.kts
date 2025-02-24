plugins {
    id("buildlogic.quarkus-common-conventions")
    id("buildlogic.java-common-conventions")
}

description = "Athenaeum Codex"

dependencies {
    implementation(project(":ddo-dal-jorm"))
    // Front-end
    implementation(libs.webjars.bootstrap)
    implementation(libs.bootstrap.icons)
    // https://mvnrepository.com/artifact/org.webjars/jquery
    implementation(libs.webjars.jquery)
    implementation(libs.webjars.htmx)
    implementation(libs.webjars.sortablejs)
    implementation(libs.quarkus.webjars)

    implementation(libs.me.atrox.haikunator.haikunator)

    implementation(libs.quarkus.renarde)
    implementation(libs.quarkus.renarde.backoffice)
    implementation(libs.quarkus.renarde.security)
    implementation(libs.io.quarkiverse.renarde.quarkus.renarde.oidc)
    implementation(libs.quarkus.renarde.pdf)
    implementation(libs.quarkus.qute.web)
//    implementation(libs.quarkus.hibernate.reactive.rest.data.panache)
//    implementation(libs.quarkus.reactive.pg.client)
    implementation(libs.quarkus.mailer)
    implementation(libs.quarkus.openapi)
//    implementation(libs.jakarta.validation)
//    implementation(libs.hibernate.validator)
    implementation(libs.quarkus.hibernate.validator)

    // Database
    implementation(libs.quarkus.hibernate.orm.panache)
//    implementation(libs.quarkus.hibernate.orm.panache.kotlin)
    implementation(libs.quarkus.jdbc.postgresql)
    implementation(libs.quarkus.flyway)
    testImplementation(libs.quarkus.renarde.tests)
    // reactive client
//    implementation(libs.quarkus.hibernate.reactive.rest.data.panache)
//    implementation(libs.quarkus.reactive.pg.client)
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

    options.compilerArgs.addAll(args)
    options.encoding = "UTF-8"
}
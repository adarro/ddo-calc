plugins {
    `java-library`

}


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.olingo:odata-server-api:4.4.0")
    testCompile("junit:junit:4.12")
    // testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
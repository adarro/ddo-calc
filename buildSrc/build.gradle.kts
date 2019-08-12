plugins {
    `kotlin-dsl`
    id("org.unbroken-dome.test-sets") version "2.1.1"
}

repositories {
    jcenter()
    mavenCentral()
    gradlePluginPortal()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
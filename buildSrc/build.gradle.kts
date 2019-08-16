plugins {
    `kotlin-dsl`
    id("org.unbroken-dome.test-sets") // version "2.1.1"
}

repositories {
    gradlePluginPortal()
    jcenter()
    mavenCentral()

}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
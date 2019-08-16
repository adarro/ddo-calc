val testSetsPluginVersion: String by settings

pluginManagement {
    plugins {
        id("org.unbroken-dome.test-sets") version testSetsPluginVersion // "2.1.1"
    }

    repositories {
        gradlePluginPortal()
        jcenter()
        mavenCentral()

    }
}
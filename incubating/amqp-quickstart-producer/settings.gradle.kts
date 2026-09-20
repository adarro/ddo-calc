pluginManagement {
    val quarkusPluginVersion= providers.gradleProperty("quarkusPluginVersion").get()
    val quarkusPluginId= providers.gradleProperty("quarkusPluginId").get()
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
    plugins {
        id(quarkusPluginId) version quarkusPluginVersion
    }
}
rootProject.name = "amqp-quickstart-producer"

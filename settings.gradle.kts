pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "kmm_places"
include(":androidApp")
include(":coreLogic")
include(":network")
include(":util")
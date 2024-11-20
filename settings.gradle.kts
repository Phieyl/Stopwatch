// settings.gradle.kts

pluginManagement {
    repositories {
        // Repositories for resolving plugins
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        // Repositories for resolving project dependencies
        google()
        mavenCentral()
    }
}

rootProject.name = "StopwatchApp"
include(":app")

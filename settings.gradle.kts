@file:Suppress("UnstableApiUsage")

include(":database")
include(":feature:home")


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Pexels Test App"
include(":app")
include(":ui")
include(":data:featured")
include(":data:photos")
include(":network")

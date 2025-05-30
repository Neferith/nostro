pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Nostro"
include(":app")
include(":domain:gamedomain")
include(":data:gamedata")
include(":domain:npcdomain")
include(":domain:playerdomain")
include(":data:playerdata")
include(":domain:mapdomain")
include(":data:mapdata")
include(":modulea")

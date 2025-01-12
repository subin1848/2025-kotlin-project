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
        // MPandroidchart
        // kotiln DSL을 사용할땐, url = uri("") 안에 작성해줘야됨
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "StudyTimerProject"
include(":app")
 
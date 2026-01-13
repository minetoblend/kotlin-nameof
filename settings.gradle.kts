pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "kotlin-nameof"

include("compiler-plugin")
include("gradle-plugin")
include("nameof-runtime")

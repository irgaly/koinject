pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}
rootProject.name = "koinject"
include(
    ":koinject",
    ":sample:android",
    ":test:android"
)

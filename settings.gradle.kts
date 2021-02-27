pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}
rootProject.name = "koinject"
include(
    ":koinject",
    ":koinject-ksp",
    ":sample:android",
    ":test:android"
)

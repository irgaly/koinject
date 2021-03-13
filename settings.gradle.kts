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
    ":koinject-android",
    ":koinject-android-work",
    ":sample:android",
    ":test:android",
    ":test"
)

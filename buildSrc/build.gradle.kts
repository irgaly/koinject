plugins {
    `kotlin-dsl`
}
repositories {
    google()
    gradlePluginPortal()
}
dependencies {
    implementation("com.android.tools.build:gradle:7.0.0-alpha12")
    implementation("org.jetbrains.kotlin.kapt:org.jetbrains.kotlin.kapt.gradle.plugin:1.4.30")
}

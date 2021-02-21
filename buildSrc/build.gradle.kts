plugins {
    `kotlin-dsl`
}
repositories {
    jcenter()
    google()
    maven("https://plugins.gradle.org/m2/")
}
dependencies {
    implementation("com.android.tools.build:gradle:4.1.1")
    implementation("org.jetbrains.kotlin.kapt:org.jetbrains.kotlin.kapt.gradle.plugin:1.4.30")
}

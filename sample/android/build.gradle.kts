plugins {
    id("com.android.application")
    kotlin("android")
    id(Dependencies.Ksp.plugin)
    id(Dependencies.Kotlin.KotlinX.Serialization.plugin)
    id(Dependencies.AndroidX.Navigation.plugin)
}

android {
    applyCommon(project)
    defaultConfig {
        applicationId = Packages.Sample.name
        versionName = Versions.versionName
    }
    applicationVariants.configureEach {
        this.sourceSets.firstOrNull { name == "main" }?.javaDirectories?.add(file("$buildDir/generated/ksp/${buildType}/kotlin"))
    }
}

dependencies {
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.material)
    implementation(Dependencies.AndroidX.Navigation.fragmentKtx)
    implementation(Dependencies.Kotlin.KotlinX.Serialization.json)
    implementation(project(":koinject"))
    implementation(project(":koinject-android"))
    implementation(project(":koinject-android-work"))
    ksp(project(":koinject-ksp"))
}

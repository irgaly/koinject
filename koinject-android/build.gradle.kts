plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

applyCommon(android)

kotlin {
    android()
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":koinject"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.AndroidX.appCompat)
                implementation(Dependencies.AndroidX.activityKtx)
                implementation(Dependencies.AndroidX.fragmentKtx)
                implementation(Dependencies.AndroidX.Lifecycle.service)
                implementation(Dependencies.AndroidX.Lifecycle.process)
                implementation(Dependencies.AndroidX.Lifecycle.viewModelSavedState)
            }
        }
    }
}

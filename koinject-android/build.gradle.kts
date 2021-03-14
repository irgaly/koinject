plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

applyCommon(android)

// AGP 7.0.0-alpha09 + MPP project workaround https://youtrack.jetbrains.com/issue/KT-43944
android {
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

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

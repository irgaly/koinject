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
                implementation(project(":koinject-android"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.AndroidX.Work.runtimeKtx)
                implementation(Dependencies.AndroidX.Lifecycle.process)
            }
        }
    }
}

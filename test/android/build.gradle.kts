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
                api(project(":test"))
            }
        }
        val androidMain by getting {
            dependencies {
            }
        }
    }
}

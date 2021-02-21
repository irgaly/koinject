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
                api(Dependencies.Kotlin.test)
                api(Dependencies.Kotlin.reflect)
                api(Dependencies.Kotlin.Coroutines.test)
                api(Dependencies.Test.Spek.dslCommon)
            }
        }
        val androidMain by getting {
            dependencies {
                api(Dependencies.Test.Spek.dslJvm)
                api(Dependencies.Test.Spek.runnerJunit5)
                api(Dependencies.Test.Mockk.mockk)
            }
        }
    }
}

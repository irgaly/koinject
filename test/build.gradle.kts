plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()
    sourceSets {
        commonMain {
            dependencies {
                api(Dependencies.Kotlin.test)
                api(Dependencies.Kotlin.reflect)
                api(Dependencies.Test.Spek.dslCommon)
            }
        }
        val jvmMain by getting {
            dependencies {
                api(Dependencies.Kotlin.Coroutines.test)
                api(Dependencies.Test.Spek.dslJvm)
                api(Dependencies.Test.Spek.runnerJunit5)
                api(Dependencies.Test.Mockk.mockk)
            }
        }
    }
}

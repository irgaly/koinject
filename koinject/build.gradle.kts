plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm()
    sourceSets {
        commonMain {
            dependencies {
                api(Dependencies.JavaX.inject)
                implementation(Dependencies.Kotlin.Stately.concurrency)
                implementation(Dependencies.Kotlin.Stately.isolateCollections)
            }
        }
        commonTest {
            dependencies {
                api(project(":test"))
            }
        }
        val jvmMain by getting {
            dependencies {
            }
        }
    }
}


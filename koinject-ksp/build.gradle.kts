plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(Dependencies.JavaX.inject)
    implementation(Dependencies.Ksp.jvm)
}


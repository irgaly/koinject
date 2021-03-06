plugins {
    `maven-publish`
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Android.classpath)
        classpath(Dependencies.Kotlin.classpath)
        classpath(Dependencies.Kotlin.KotlinX.Serialization.classpath)
        classpath(Dependencies.AndroidX.Navigation.classpath)
        classpath(Dependencies.Ksp.classpath)
        classpath(Dependencies.Remal.classpath)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
    apply(plugin = Dependencies.Remal.checkUpdatesPlugin)
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
    tasks.withType<Test> {
        useJUnitPlatform {
            includeEngines("spek2")
        }
    }
}

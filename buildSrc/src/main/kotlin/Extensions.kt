import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

/**
 * android build 共通設定を適用する
 */
fun Project.applyCommon(extension: BaseExtension, resourcePrefix: String? = null) {
    extension.applyCommon(this, resourcePrefix)
}

/**
 * android build 共通設定を適用する
 */
fun BaseExtension.applyCommon(
    project: Project,
    resourcePrefix: String? = null,
    dataBinding: Boolean = true
) {
    resourcePrefix?.let {
        resourcePrefix("${it}_")
    }
    compileSdkVersion(Versions.androidCompileSdkVersion)
    defaultConfig {
        minSdkVersion(Versions.androidMinSdkVersion)
        targetSdkVersion(Versions.androidTargetSdkVersion)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    lintOptions {
        isCheckDependencies = true
        lintConfig = project.file("${project.rootDir}/lint/lint.xml")
        isAbortOnError = false
    }
    if (this is com.android.build.gradle.internal.dsl.BaseAppModuleExtension) {
        buildFeatures {
            this.dataBinding = dataBinding
        }
        sourceSets.configureEach {
            java.srcDirs("src/$name/kotlin")
        }
    }
    if (this is LibraryExtension) {
        buildFeatures {
            this.dataBinding = dataBinding
            buildConfig = false
        }
        sourceSets.configureEach {
            setRoot("src/android/$name")
            java.srcDirs("src/android/$name/kotlin")
        }
    }
}

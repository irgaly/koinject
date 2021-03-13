import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

@Suppress("unused", "MayBeConstant", "MemberVisibilityCanBePrivate")
object Dependencies {
    object Android {
        val classpath = "com.android.tools.build:gradle:4.1.1"
    }

    object Kotlin {
        val version = "1.4.30"
        val classpath = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
        val test = "org.jetbrains.kotlin:kotlin-test:$version"

        object Coroutines {
            val version = "1.4.3"
            val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            val rx = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$version"
            val playServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$version"
            val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }

        object KotlinX {
            val repository = "https://kotlin.bintray.com/kotlinx"

            object Serialization {
                val plugin = "kotlinx-serialization"
                val version = "1.1.0-RC"
                val classpath = "org.jetbrains.kotlin:kotlin-serialization:${Kotlin.version}"
                val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:$version"
            }

            object Io {
                val version = "0.1.16"
                val common = "org.jetbrains.kotlinx:kotlinx-io:$version"
                val jvm = "org.jetbrains.kotlinx:kotlinx-io-jvm:$version"
                val native = "org.jetbrains.kotlinx:kotlinx-io-native:$version"
            }
        }

        object Stately {
            val version = "1.1.4"
            val common = "co.touchlab:stately-common:$version"
            val concurrency = "co.touchlab:stately-concurrency:$version"
            val isolateCollections = "co.touchlab:stately-iso-collections:1.1.4-a1"
        }
    }

    object Ksp {
        val version = "1.4.30-1.0.0-alpha02"
        val classpath = "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:$version"
        val plugin = "com.google.devtools.ksp"
        val jvm = "com.google.devtools.ksp:symbol-processing-api:$version"
    }

    object AndroidX {
        val appCompat = "androidx.appcompat:appcompat:1.2.0"
        val startup = "androidx.startup:startup-runtime:1.0.0"
        val recyclerView = "androidx.recyclerview:recyclerview:1.0.0"
        val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
        val pagingKtx = "androidx.paging:paging-runtime-ktx:3.0.0-alpha09"
        val constraint = "androidx.constraintlayout:constraintlayout:2.0.4"
        val material = "com.google.android.material:material:1.3.0"
        val coreKtx = "androidx.core:core-ktx:1.5.0-alpha05"
        val preference = "androidx.preference:preference:1.1.0"
        val preferenceKtx = "androidx.preference:preference-ktx:1.1.1"
        val datastorePreferences = "androidx.datastore:datastore-preferences:1.0.0-alpha05"
        val browser = "androidx.browser:browser:1.2.0"
        val webkit = "androidx.webkit:webkit:1.3.0-alpha02"
        val activityKtx = "androidx.activity:activity-ktx:1.3.0-alpha03"
        val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.0-alpha06"
        val media = "androidx.media:media:1.2.0"
        val emoji = "androidx.emoji:emoji:1.2.0-alpha01"
        val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

        object Lifecycle {
            val version = "2.2.0"
            val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            val service = "androidx.lifecycle:lifecycle-service:$version"
            val process = "androidx.lifecycle:lifecycle-process:$version"
            val rx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:$version"
            val viewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
            val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
        }

        object Navigation {
            val plugin = "androidx.navigation.safeargs"
            val version = "2.3.1"
            val classpath = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
            val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
        }

        object Room {
            val version = "2.2.0-beta01"
            val runtime = "androidx.room:room-runtime:$version"
            val ktx = "androidx.room:room-ktx:$version"
            val rxjava2 = "androidx.room:room-rxjava2:$version"
            val kapt = "androidx.room:room-compiler:$version"
            val kaptDependency =
                DefaultExternalModuleDependency("androidx.room", "room-compiler", version)
        }

        object Work {
            val version = "2.5.0"
            val runtimeKtx = "androidx.work:work-runtime-ktx:$version"
        }
    }

    object GoogleServices {
        // Firebase に必要
        val plugin = "com.google.gms.google-services"
        val classpath = "com.google.gms:google-services:4.3.4"
        val auth = "com.google.android.gms:play-services-auth:19.0.0"
        val maps = "com.google.android.gms:play-services-maps:17.0.0"
        val location = "com.google.android.gms:play-services-location:17.0.0"
    }

    object GoogleMaps {
        val utils = "com.google.maps.android:android-maps-utils:2.2.0"
    }

    object Firebase {
        val bom = "com.google.firebase:firebase-bom:26.1.0"
        val authKtx = "com.google.firebase:firebase-auth-ktx"
        val messaging = "com.google.firebase:firebase-messaging"
        val analyticsKtx = "com.google.firebase:firebase-analytics-ktx"
        val messagingDisplay = "com.google.firebase:firebase-inappmessaging-display"

        object Crashlytics {
            val plugin = "com.google.firebase.crashlytics"
            val classpath = "com.google.firebase:firebase-crashlytics-gradle:2.4.1"
            val ktx = "com.google.firebase:firebase-crashlytics-ktx"
        }
    }

    /**
     * Git ホスティングなライブラリで必要になるリポジトリ
     */
    object Jitpack {
        val repository = "https://jitpack.io"
    }

    object Klock {
        val version = "1.12.0"
        val common = "com.soywiz.korlibs.klock:klock:$version"
    }

    object Timber {
        val version = "4.7.1"
        val android = "com.jakewharton.timber:timber:$version"
    }

    /**
     * DI
     */
    object Koin {
        val version = "3.0.0-alpha-4"
        val repository = "https://dl.bintray.com/ekito/koin"
        val coreExt = "org.koin:koin-core-ext:$version"
        val androidExt = "org.koin:koin-androidx-ext:$version"
        val androidViewModel = "org.koin:koin-androidx-viewmodel:$version"
        val androidScope = "org.koin:koin-androidx-scope:$version"
    }

    /**
     * DI
     */
    object Dagger {
        val version = "2.27"
        val android = "com.google.dagger:dagger-android:$version"
        val androidSupport = "com.google.dagger:dagger-android-support:$version"
        val kapt = "com.google.dagger:dagger-compiler:$version"
        val kaptDependency =
            DefaultExternalModuleDependency("com.google.dagger", "dagger-compiler", version)
        val kaptAndroid = "com.google.dagger:dagger-android-processor:$version"
        val kaptAndroidDependency =
            DefaultExternalModuleDependency(
                "com.google.dagger",
                "dagger-android-processor",
                version
            )
    }

    /**
     * 関数型言語機能 (Tryなど)
     *
     * Multiplatform Common 非対応
     */
    object Arrow {
        val version = "0.8.2"
        val coreJvm = "io.arrow-kt:arrow-core:$version"
    }

    /**
     * Reactive
     *
     * Multiplatform Common 非対応
     */
    object Rx {
        val java = "io.reactivex.rxjava2:rxjava:2.2.20"
        val android = "io.reactivex.rxjava2:rxandroid:2.1.1"
        val kotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"

        object Reaktive {
            val version = "1.1.9"
            val repository = "https://dl.bintray.com/badoo/maven"
            val common = "com.badoo.reaktive:reaktive:$version"
            val commonCoroutines = "com.badoo.reaktive:coroutines-interop:$version"
            val jvm = "com.badoo.reaktive:reaktive-jvm:$version"
            val rxJava2 = "com.badoo.reaktive:rxjava2-interop:$version"
        }
    }

    /**
     * API アクセス
     */
    object Ktor {
        val version = "1.5.1"
        val clientCore = "io.ktor:ktor-client-core:$version"
        val clientWebsockets = "io.ktor:ktor-client-websockets:$version"
        val clientAndroid = "io.ktor:ktor-client-android:$version"
        val clientIos = "io.ktor:ktor-client-ios:$version"
        val clientOkhttp = "io.ktor:ktor-client-okhttp:$version"
        val clientLogging = "io.ktor:ktor-client-logging:$version"
        val serialization = "io.ktor:ktor-client-serialization:$version"
        val serializationJvm = "io.ktor:ktor-client-serialization-jvm:$version"
        val serializationIosX64 = "io.ktor:ktor-client-serialization-iosx64:$version"
        val serializationIosArm64 = "io.ktor:ktor-client-serialization-iosarm64:$version"

        /**
         * ktor-http: Url クラスなどの HTTP 処理だけ使いたいときに参照するパッケージ
         */
        val http = "io.ktor:ktor-http:$version"

        /**
         * ktor-http: Url クラスなどの HTTP 処理だけ使いたいときに参照するパッケージ
         */
        val httpJvm = "io.ktor:ktor-http-jvm:$version"
    }

    /**
     * HTTP 通信
     *
     * Multiplatform Common 非対応
     */
    object Okhttp {
        val version = "4.5.0"
        val okhttp = "com.squareup.okhttp3:okhttp:$version"
        val logging = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    /**
     * RecyclerView ヘルパー
     */
    object Epoxy {
        val version = "4.3.1"
        val epoxy = "com.airbnb.android:epoxy:$version"
        val databinding = "com.airbnb.android:epoxy-databinding:$version"
        val paging = "com.airbnb.android:epoxy-paging:$version"
        val paging3 = "com.airbnb.android:epoxy-paging3:$version"
        val kapt = "com.airbnb.android:epoxy-processor:$version"
        val kaptDependency =
            DefaultExternalModuleDependency("com.airbnb.android", "epoxy-processor", version)
    }

    /**
     * ライセンス管理
     *
     * buildscript.repositories { gradlePluginPortal() } の設定が必要
     */
    object LicenseTools {
        val classpath = "gradle.plugin.com.cookpad.android.plugin:plugin:1.2.5"
        val plugin = "com.cookpad.android.plugin.license-tools"
    }

    /**
     * UI 関係ライブラリ
     */
    object Ui {
        /**
         * @see Jitpack
         */
        val databindingKtx = "com.github.wada811:DataBinding-ktx:5.0.0"

        /**
         * @see Jitpack
         */
        val savedStateKtx = "com.github.wada811:ViewModel-SavedState-ktx:2.3.0-rc01"
        val flexbox = "com.google.android:flexbox:1.1.0"
        val circleindicator = "me.relex:circleindicator:2.1.4"
        val coil = "io.coil-kt:coil:1.1.0"
        val glide = "com.github.bumptech.glide:glide:4.11.0"
        val photoView = "com.github.chrisbanes:PhotoView:2.3.0"
        val lottie = "com.airbnb.android:lottie:3.4.0"
        val insetter = "dev.chrisbanes.insetter:insetter-dbx:0.5.0"
    }

    object JavaX {
        val inject = "javax.inject:javax.inject:1"
        val annotation = "javax.annotation:javax.annotation-api:1.3.2"
    }

    /**
     * SLF4J Android
     * Ktor Logging の出力に必要
     */
    object Slf4j {
        val android = "org.slf4j:slf4j-android:1.7.30"
    }

    object Test {
        object Spek {
            val version = "2.0.15"
            val dslCommon = "org.spekframework.spek2:spek-dsl-metadata:$version"
            val dslJvm = "org.spekframework.spek2:spek-dsl-jvm:$version"
            val runnerJunit5 = "org.spekframework.spek2:spek-runner-junit5:$version"
        }

        object Mockk {
            val mockk = "io.mockk:mockk:1.10.2"
        }
    }

    object Ktlint {
        val ktlintGradleVersion = "9.4.1"
        val classpath = "org.jlleitschuh.gradle:ktlint-gradle:$ktlintGradleVersion"
        val plugin = "org.jlleitschuh.gradle.ktlint"
    }

    /**
     * 開発用インスペクター
     */
    object Stetho {
        val version = "1.5.1"
        val stetho = "com.facebook.stetho:stetho:$version"
        val okhttp3 = "com.facebook.stetho:stetho-okhttp3:$version"
        val timber = "com.facebook.stetho:stetho-timber:$version"
    }

    /**
     * 開発便利プラグイン
     */
    object Remal {
        val version = "1.1.4"
        val classpath = "name.remal:gradle-plugins:$version"
        val checkUpdatesPlugin = "name.remal.check-updates"
    }
}

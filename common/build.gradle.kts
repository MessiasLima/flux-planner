@file:Suppress("UNUSED_VARIABLE", "OPT_IN_IS_NOT_ENABLED")

import org.jetbrains.compose.compose

plugins {
    kotlin(Kotlin.Plugin.multiplatform)
    kotlin(Kotlin.Plugin.serialization)
    id(Compose.Plugin.compose)
    id(Android.Plugin.library)
    id(SqlDelight.plugin)
}

group = App.group
version = App.version

kotlin {
    android {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }

    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }

    sourceSets {
        val commonMain by getting {
            @kotlin.OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                api(compose.materialIconsExtended)
                api(Koin.core)
                implementation(Ktor.clientCore)
                implementation(Ktor.contentNegotiation)
                implementation(Ktor.serializationJson)
                implementation(Kotlin.Dependency.Serialization.json)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin(Kotlin.Dependency.test))
            }
        }

        val androidMain by getting {
            dependencies {
                api(AndroidX.AppCompat.appCompat)
                api(AndroidX.Core.coreKtx)
                implementation(Ktor.clientAndroid)
                implementation(AndroidX.Lifecycle.viewModelKtx)
                implementation(SqlDelight.androidDriver)
            }
        }

        val androidTest by getting

        val desktopMain by getting {
            dependencies {
                api(compose.preview)
                implementation(Ktor.clientJava)
                implementation(SqlDelight.sqliteDriver)
            }
        }

        val desktopTest by getting
    }
}

android {
    compileSdk = Android.compileSdk

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

sqldelight {
    database("FluxDB") {
        packageName = "io.appoutlet.flux.common.core.database"
        schemaOutputDirectory =
            file("src/commonMain/sqldelight/io/appoutlet/flux/common/core/database")
    }
}

apply(from = "$rootDir/scripts/detekt.gradle")

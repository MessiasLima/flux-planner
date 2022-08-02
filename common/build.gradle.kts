@file:Suppress("UNUSED_VARIABLE", "OPT_IN_IS_NOT_ENABLED")

import org.jetbrains.compose.compose

plugins {
    kotlin(Kotlin.Plugin.multiplatform)
    id(Compose.Plugin.compose)
    id(Android.Plugin.library)
}

group = App.group
version = App.version

kotlin {
    android()

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
            }
        }

        val androidTest by getting

        val desktopMain by getting {
            dependencies {
                api(compose.preview)
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

apply(from = "$rootDir/scripts/detekt.gradle")

plugins {
    id(Compose.Plugin.compose)
    id(Android.Plugin.application)
    kotlin(Kotlin.Plugin.android)
}

group = App.group
version = App.version

dependencies {
    implementation(project(":common"))
    implementation(AndroidX.Activity.activityCompose)
}

android {
    compileSdkVersion(Android.compileSdk)

    defaultConfig {
        applicationId = Android.applicationId
        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)
        versionCode = Android.versionCode
        versionName = Android.versionName
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

apply(from = "$rootDir/scripts/detekt.gradle")
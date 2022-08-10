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
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = Android.applicationId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
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

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

apply(from = "$rootDir/scripts/detekt.gradle")

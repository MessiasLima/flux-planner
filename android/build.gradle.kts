plugins {
    id(Compose.Plugin.compose)
    id(Android.Plugin.application)
    kotlin(Kotlin.Plugin.android)
    id(Kover.plugin)
}

group = App.group
version = App.version

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

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":common"))
    implementation(AndroidX.Activity.activityCompose)

    testImplementation(kotlin(Kotlin.Dependency.test))
    testImplementation(KotlinFixture.fixture)
    testImplementation(Junit.junit)
    testImplementation(Kotlin.Dependency.Coroutines.test)
    testImplementation(Ktor.clientMock)
    testImplementation(MockK.jvm)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

apply(from = "$rootDir/scripts/detekt.gradle")

// region kover setup
val includePatterns = listOf("io.appoutlet.flux.*")
val excludePatterns = listOf(
    "**Test",
    "**BuildConfig**",
    "**ModulesKt",
    "**ModuleKt",
)

kover {
    isDisabled.set(false)
    engine.set(kotlinx.kover.api.DefaultIntellijEngine)
    filters {
        classes {
            includes += includePatterns
            excludes += excludePatterns
        }
    }

    verify {
        onCheck.set(true)
        isDisabled.set(true)
        rule {
            isEnabled = true
            name = null
            target = kotlinx.kover.api.VerificationTarget.ALL

            bound {
                minValue = 80
                counter = kotlinx.kover.api.CounterType.LINE
                valueType = kotlinx.kover.api.VerificationValueType.COVERED_PERCENTAGE
            }
        }
    }
}
// endregion

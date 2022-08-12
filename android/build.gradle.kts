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
    isDisabled.set(false) // true to disable instrumentation and all Kover tasks in this project
    engine.set(kotlinx.kover.api.DefaultIntellijEngine) // change Coverage Engine
    filters { // common filters for all default Kover tasks
        classes { // common class filter for all default Kover tasks
            includes += includePatterns // class inclusion rules
            excludes += excludePatterns // class exclusion rules
        }
    }

    verify {
        onCheck.set(true) // true to run koverVerify task during the execution of the check task
        isDisabled.set(false)
        rule { // add verification rule
            isEnabled = true // false to disable rule checking
            name = null // custom name for the rule
            target = kotlinx.kover.api.VerificationTarget.ALL // specify by which entity the code for separate coverage evaluation will be grouped

            bound { // add rule bound
                minValue = 80
                counter = kotlinx.kover.api.CounterType.LINE // change coverage metric to evaluate (LINE, INSTRUCTION, BRANCH)
                valueType = kotlinx.kover.api.VerificationValueType.COVERED_PERCENTAGE // change counter value (COVERED_COUNT, MISSED_COUNT, COVERED_PERCENTAGE, MISSED_PERCENTAGE)
            }
        }
    }
}
// endregion

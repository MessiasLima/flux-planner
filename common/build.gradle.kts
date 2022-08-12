@file:Suppress("UNUSED_VARIABLE", "OPT_IN_IS_NOT_ENABLED")

import org.jetbrains.compose.compose

plugins {
    kotlin(Kotlin.Plugin.multiplatform)
    kotlin(Kotlin.Plugin.serialization)
    id(Compose.Plugin.compose)
    id(Android.Plugin.library)
    id(SqlDelight.plugin)
    id(Kover.plugin) //version Kover.version
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
                implementation(KotlinFixture.fixture)
                implementation(Kotlin.Dependency.Coroutines.test)
                implementation(Ktor.clientMock)
                api(MockK.jvm)
            }
        }

        val androidMain by getting {
            dependencies {
                dependsOn(commonMain)
                api(AndroidX.AppCompat.appCompat)
                api(AndroidX.Core.coreKtx)
                implementation(Ktor.clientAndroid)
                implementation(AndroidX.Lifecycle.viewModelKtx)
                implementation(SqlDelight.androidDriver)
            }
        }

        val androidTest by getting {
            dependencies {
                dependsOn(commonTest)
            }
        }

        val desktopMain by getting {
            dependencies {
                dependsOn(commonMain)
                api(compose.preview)
                implementation(Ktor.clientJava)
                implementation(SqlDelight.sqliteDriver)
            }
        }

        val desktopTest by getting {
            dependencies {
                dependsOn(commonTest)
                implementation(MockK.jvm)
            }
        }
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

koverMerged {
    enable()
}

// endregion

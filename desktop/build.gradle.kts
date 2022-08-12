@file:Suppress("UNUSED_VARIABLE")

import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin(Kotlin.Plugin.multiplatform)
    id(Compose.Plugin.compose)
    id(Kover.plugin)
}

group = App.group
version = App.version

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }

        withJava()
    }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(compose.desktop.currentOs)
                implementation(Karavel.karavel)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin(Kotlin.Dependency.test))
                implementation(KotlinFixture.fixture)
                implementation(Kotlin.Dependency.Coroutines.test)
                implementation(Ktor.clientMock)
                implementation(MockK.jvm)
            }
        }
    }
}

compose.desktop {
    application {

        mainClass = "Flux"

        nativeDistributions {
            targetFormats(
                TargetFormat.Dmg,
                TargetFormat.Msi,
                TargetFormat.Deb,
                TargetFormat.AppImage,
            )

            packageName = "flux-planner"
            packageVersion = App.version
        }
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
            target =
                kotlinx.kover.api.VerificationTarget.ALL // specify by which entity the code for separate coverage evaluation will be grouped

            bound { // add rule bound
                minValue = 80
                counter =
                    kotlinx.kover.api.CounterType.LINE // change coverage metric to evaluate (LINE, INSTRUCTION, BRANCH)
                valueType =
                    kotlinx.kover.api.VerificationValueType.COVERED_PERCENTAGE // change counter value (COVERED_COUNT, MISSED_COUNT, COVERED_PERCENTAGE, MISSED_PERCENTAGE)
            }
        }
    }
}

koverMerged {
    enable()
}

// endregion

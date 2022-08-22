@file:Suppress("UNUSED_VARIABLE", "OPT_IN_IS_NOT_ENABLED")

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
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            dependencies {
                implementation(kotlin(Kotlin.Dependency.test))
                implementation(KotlinFixture.fixture)
                implementation(Kotlin.Dependency.Coroutines.test)
                implementation(Ktor.clientMock)
                implementation(MockK.jvm)
                implementation(compose.uiTestJUnit4)
                implementation(compose.desktop.currentOs)
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
        isDisabled.set(false)
        rule {
            isEnabled = true
            name = null
            target = kotlinx.kover.api.VerificationTarget.ALL

            bound {
                minValue = 28
                counter = kotlinx.kover.api.CounterType.LINE
                valueType = kotlinx.kover.api.VerificationValueType.COVERED_PERCENTAGE
            }
        }
    }
}
// endregion

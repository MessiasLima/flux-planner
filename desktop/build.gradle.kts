import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin(Kotlin.Plugin.multiplatform)
    id(Compose.Plugin.compose)
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
            }
        }

        val jvmTest by getting
    }
}

compose.desktop {
    application {

        mainClass = "io.appoutlet.flux.desktop.MainKt"

        nativeDistributions {
            targetFormats(
                TargetFormat.Dmg,
                TargetFormat.Msi,
                TargetFormat.Deb
            )

            packageName = "flux-planner"
            packageVersion = App.version
        }
    }
}

apply(from = "$rootDir/scripts/detekt.gradle")

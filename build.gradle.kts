@file:Suppress("UNUSED_EXPRESSION")

group "io.appoutlet.flux"
version "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
    kotlin(Kotlin.multiplatformPlugin) version Kotlin.version apply false
    kotlin(Kotlin.androidPlugin) version Kotlin.version apply false
    id(Android.Plugin.application) version Android.Plugin.version  apply false
    id(Android.Plugin.library) version Android.Plugin.version apply false
    id(Compose.Plugin.compose) version Compose.Plugin.version apply false
    id(Commitlint.plugin) version Commitlint.version apply false
    id(Githooks.plugin) version Githooks.version apply false
}

apply(from = "${rootDir}/scripts/git-hooks.gradle")
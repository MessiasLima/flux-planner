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
    kotlin(Kotlin.Plugin.multiplatform) version Kotlin.version apply false
    kotlin(Kotlin.Plugin.android) version Kotlin.version apply false
    kotlin(Kotlin.Plugin.serialization) version Kotlin.version apply false
    id(Android.Plugin.application) version Android.Plugin.version  apply false
    id(Android.Plugin.library) version Android.Plugin.version apply false
    id(Compose.Plugin.compose) version Compose.Plugin.version apply false
    id(Commitlint.plugin) version Commitlint.version apply false
    id(Githooks.plugin) version Githooks.version apply false
    id(SqlDelight.plugin) version SqlDelight.version apply false
    id(Detekt.plugin) version Detekt.version
    id(Kover.plugin) version Kover.version
}

apply(from = "${rootDir}/scripts/git-hooks.gradle")
apply(from = "${rootDir}/scripts/kover.gradle")

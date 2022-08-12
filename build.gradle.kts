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

// region kover setup
val includePatterns = listOf("io.appoutlet.flux.*")
val excludePatterns = listOf("io.appoutlet.flux.*.*Test")

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

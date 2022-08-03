object Kotlin {
    const val version = "1.6.10"

    object Plugin {
        const val multiplatform = "multiplatform"
        const val android = "android"
        const val serialization = "plugin.serialization"
    }

    object Dependency {
        const val test = "test"

        object Serialization {
            private const val version = "1.3.3"

            const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:$version"
        }
    }
}

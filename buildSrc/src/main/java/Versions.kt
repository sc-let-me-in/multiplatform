const val KOTLIN_VERSION = "1.4.10"
private const val ANDROID_GRADLE_VERSION = "4.0.1"

object Versions {
    object Build {
        const val ANDROID_GRADLE = "com.android.tools.build:gradle:$ANDROID_GRADLE_VERSION"
        const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
        const val NAV_SAFE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0"
    }

    object Android {
        const val BUILD_TOOLS = "29.0.2"
        const val MIN_SDK = 28
        const val TARGET_SDK = 29
        const val COMPILE_SDK = 29
        const val LIFECYCLE = "2.2.0"

        const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:$LIFECYCLE"
        const val LIFECYCLE_VM = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE"
        const val TIMBER = "com.jakewharton.timber:timber:4.7.1"
    }

    object Libs {
        private const val COROUTINES = "1.3.9-native-mt-2"
        const val KTOR = "1.4.1"
        const val KOTLIN_SERIALIZATION = "1.0.0-RC"
        const val LOGBACK = "1.2.3"

        const val KOTLIN_STD = "org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_VERSION"
        const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES"
        const val SERIALIZATION_CORE = "org.jetbrains.kotlinx:kotlinx-serialization-core:$KOTLIN_SERIALIZATION"
        const val MULTIPLATFORM_SETTINGS = "com.russhwolf:multiplatform-settings:0.6.1"
        const val UUID = "com.benasher44:uuid:0.2.2"
    }
}

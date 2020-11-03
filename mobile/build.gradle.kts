import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("plugin.serialization") version KOTLIN_VERSION
}

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility  = JavaVersion.VERSION_1_8
    }

    compileSdkVersion(Versions.Android.COMPILE_SDK)
    buildToolsVersion = Versions.Android.BUILD_TOOLS
    defaultConfig {
        minSdkVersion(Versions.Android.MIN_SDK)
        targetSdkVersion(Versions.Android.TARGET_SDK)
    }

    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            java.srcDirs("src/androidMain/kotlin")
            res.srcDirs("src/androidMain/res")
        }
    }

}

kotlin {
    android()

    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "KotlinAppShared"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
                api(project(":common"))

                implementation(Versions.Libs.COROUTINES_CORE)
                implementation(Versions.Libs.SERIALIZATION_CORE)

                api("io.ktor:ktor-client-core:${Versions.Libs.KTOR}")
                implementation("io.ktor:ktor-client-json:${Versions.Libs.KTOR}")
                implementation("io.ktor:ktor-client-serialization:${Versions.Libs.KTOR}")
                implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.3")

                api(Versions.Libs.MULTIPLATFORM_SETTINGS)
                api(Versions.Libs.UUID)
                api("io.ktor:ktor-client-cio:${Versions.Libs.KTOR}")
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
        }

        val iosMain by getting {
            dependsOn(commonMain)
        }
    }
}

val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    /// selecting the right configuration for the iOS
    /// framework depending on the environment
    /// variables set by Xcode build
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets
        .getByName<KotlinNativeTarget>("ios")
        .binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)

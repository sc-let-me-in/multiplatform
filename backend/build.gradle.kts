import org.jetbrains.kotlin.gradle.dsl.KotlinCommonOptions
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilationToRunnableFiles

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version KOTLIN_VERSION
}

kotlin {
    jvm()

    sourceSets {
        val jvmMain by getting {
            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")

            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(kotlin("stdlib-jdk8"))

                implementation(project(":common"))
                implementation(Versions.Libs.SERIALIZATION_CORE)

                implementation("io.ktor:ktor-server-netty:${Versions.Libs.KTOR}")
                implementation("io.ktor:ktor-serialization:${Versions.Libs.KTOR}")
                implementation("io.ktor:ktor-websockets:${Versions.Libs.KTOR}")

                implementation("ch.qos.logback:logback-classic:${Versions.Libs.LOGBACK}")
            }
        }
    }
}

task<JavaExec>("run") {
    main = "foo.bar.kotlin.backend.ServerKt"
    val jvm by kotlin.targets.getting
    val main: KotlinCompilation<KotlinCommonOptions> by jvm.compilations

    val runtimeDependencies = (main as KotlinCompilationToRunnableFiles<KotlinCommonOptions>).runtimeDependencyFiles
    classpath = files(main.output.allOutputs, runtimeDependencies)
}

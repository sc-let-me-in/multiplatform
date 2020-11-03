rootProject.name = "KotlinApp"

// needed for kotlin/native coroutines
enableFeaturePreview("GRADLE_METADATA")

include("common", "backend", "mobile", "android")

plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvm()
    // Function to check if we're on macOS with Apple Silicon (ARM)
    fun isMacOsArm(): Boolean {
        val osName = System.getProperty("os.name").lowercase()
        val osArch = System.getProperty("os.arch").lowercase()
        return osName.contains("mac") && (osArch.contains("aarch64") || osArch.contains("arm64"))
    }

    if (isMacOsArm()) {
        iosArm64()
        iosSimulatorArm64()
    }

    sourceSets {
        commonMain {
            dependencies {
                api(libs.kotlin.stdlib)
                api(libs.kotlin.coroutines.core)
                api(libs.kotlininject.runtime)
            }
        }
    }

}
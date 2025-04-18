plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvm()
    iosArm64()
    iosSimulatorArm64()

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
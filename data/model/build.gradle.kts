plugins {
    id("dev.forcetower.instalytics.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.base)
                implementation(libs.androidx.room.runtime)
                implementation(libs.kotlin.stdlib)
            }
        }
    }
}
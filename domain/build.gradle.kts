plugins {
    id("dev.forcetower.instalytics.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.base)
                implementation(projects.data.instagram.profile)
                implementation(libs.kotlin.stdlib)
            }
        }
    }
}
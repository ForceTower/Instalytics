plugins {
    id("dev.forcetower.instalytics.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.base)
            }
        }
    }
}
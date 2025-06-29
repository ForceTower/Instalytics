plugins {
    id("dev.forcetower.instalytics.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.base)
                implementation(projects.data.db)
                implementation(projects.data.model)
                implementation(projects.data.instagram.network)
                implementation(projects.data.instagram.profile)
                implementation(libs.kotlin.stdlib)
            }
        }
    }
}
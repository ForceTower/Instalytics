plugins {
    id("dev.forcetower.instalytics.android.library")
    id("dev.forcetower.instalytics.multiplatform")
}

android {
    namespace = "dev.forcetower.instalytics.data.instagram.network"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.base)
                implementation(projects.data.db)
                implementation(projects.data.model)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.contentnegotiation)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.serialization.json)
                implementation(libs.okio)
                implementation(libs.kache)
            }
        }
    }
}

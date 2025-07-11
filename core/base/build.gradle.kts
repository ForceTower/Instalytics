plugins {
    id("dev.forcetower.instalytics.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.kotlin.stdlib)
                api(libs.kotlin.coroutines.core)
//                api(libs.kotlininject.runtime)
                api(libs.kermit.kermit)
                api(project.dependencies.platform(libs.koin.bom))
                api(libs.koin.core)
            }
        }
    }
}

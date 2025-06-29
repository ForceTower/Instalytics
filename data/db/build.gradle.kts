import dev.forcetower.instalytics.gradle.addKspDependencyForAllTargets

plugins {
    id("dev.forcetower.instalytics.android.library")
    id("dev.forcetower.instalytics.multiplatform")
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.base)
                implementation(projects.data.model)
                api(libs.androidx.room.runtime)
                api(libs.androidx.room.paging)
                api(libs.androidx.sqlite.bundled)
            }
        }
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

addKspDependencyForAllTargets(libs.kotlininject.compiler.ksp)
addKspDependencyForAllTargets(libs.androidx.room.compiler)

android {
    namespace = "dev.forcetower.instalytics.data.database"
}
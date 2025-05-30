import dev.forcetower.instalytics.gradle.addKspDependencyForAllTargets
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("dev.forcetower.instalytics.android.library")
    id("dev.forcetower.instalytics.multiplatform")
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
}

kotlin {
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            isStatic = true
            baseName = "InstalyticsKit"

//            export(projects.core.analytics)
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.core.base)
            api(projects.data.db)
            api(projects.data.instagram.profile)
            api(libs.forcetower.toolkit.logdog)
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)
            implementation(libs.kotlininject.runtime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "dev.forcetower.instalytics"
}

ksp {
    arg("me.tatarka.inject.generateCompanionExtensions", "true")
}

room {
    schemaDirectory("$projectDir/schemas")
}

addKspDependencyForAllTargets(libs.kotlininject.compiler.ksp)
addKspDependencyForAllTargets(libs.androidx.room.compiler)

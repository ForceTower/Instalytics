import dev.forcetower.instalytics.gradle.addKspDependencyForAllTargets
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("dev.forcetower.instalytics.android.library")
    id("dev.forcetower.instalytics.multiplatform")
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.touchlab.skie)
//    alias(libs.plugins.nativeCoroutines)
}

kotlin {
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            isStatic = true
            baseName = "InstalyticsKit"
            binaryOption("bundleId", "dev.forcetower.instalytics.shared")

            export(projects.domain)
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.core.base)
            api(projects.domain)
            api(libs.androidx.viewmodel.core)
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)
//            implementation(libs.kotlininject.runtime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        iosMain.dependencies {
        }
    }

    jvmToolchain(17)
}

android {
    namespace = "dev.forcetower.instalytics.shared"
}

ksp {
//    arg("me.tatarka.inject.generateCompanionExtensions", "true")
}

room {
    schemaDirectory("$projectDir/schemas")
}

// addKspDependencyForAllTargets(libs.kotlininject.compiler.ksp)
addKspDependencyForAllTargets(libs.androidx.room.compiler)

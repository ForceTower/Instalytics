import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.compose.compiler)
}

val localProperties = Properties()
try {
    localProperties.load(FileInputStream(rootProject.file("local.properties")))
} catch (ignored: Exception) {
    logger.warn("No Local Properties File Found!")
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "dev.forcetower.instalytics.android"
    compileSdk = 36
    defaultConfig {
        applicationId = "dev.forcetower.instalytics.android"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"

        resValue("string", "facebook_client_token", localProperties.getProperty("facebook_client_token", ""))
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(projects.shared)
    implementation(projects.domain)
    implementation(projects.android.shared.toolkit)
    implementation(libs.androidx.appcompat)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.ui.text.googleFonts)

    implementation(libs.facebook.sdk)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.charts.compose)
    implementation(libs.androidx.room.runtime)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.common)

    debugImplementation(libs.compose.ui.tooling)
}
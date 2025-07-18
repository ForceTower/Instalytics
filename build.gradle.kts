plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinParcelize) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.androidx.room) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.cacheFixPlugin) apply false
    alias(libs.plugins.ktorfit) apply false
    alias(libs.plugins.touchlab.skie) apply false
    alias(libs.plugins.nativeCoroutines) apply false
}

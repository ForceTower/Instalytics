package dev.forcetower.instalytics.gradle

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.HasUnitTestBuilder
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

fun Project.configureAndroid() {
  android {
    compileSdkVersion(Versions.COMPILE_SDK)

    defaultConfig {
      minSdk = Versions.MIN_SDK
      targetSdk = Versions.TARGET_SDK

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

      manifestPlaceholders += mapOf("appAuthRedirectScheme" to "empty")
    }

    if (this is CommonExtension<*, *, *, *, *, *>) {
      lint {
        // Disable lintVital. Not needed since lint is run on CI
        checkReleaseBuilds = false
        // Ignore any tests
        ignoreTestSources = true
        // Make the build fail on any lint errors
        abortOnError = true
      }
    }

    compileOptions {
      // https://developer.android.com/studio/write/java8-support
//      isCoreLibraryDesugaringEnabled = true
    }

    testOptions {
      if (this@android is LibraryExtension) {
        // We only want to configure this for library modules
        targetSdk = Versions.TARGET_SDK
      }

      unitTests {
        isIncludeAndroidResources = true
        isReturnDefaultValues = true
      }
    }
  }

  androidComponents {
    beforeVariants(selector().withBuildType("release")) { variantBuilder ->
      (variantBuilder as? HasUnitTestBuilder)?.apply {
        enableUnitTest = false
      }
    }
  }

  dependencies {
    // https://developer.android.com/studio/write/java8-support
//    "coreLibraryDesugaring"(libs.findLibrary("tools.desugarjdklibs").get())
  }
}

private fun Project.android(action: BaseExtension.() -> Unit) = extensions.configure<BaseExtension>(action)

private fun Project.androidComponents(action: AndroidComponentsExtension<*, *, *>.() -> Unit) {
  extensions.configure(AndroidComponentsExtension::class.java, action)
}

plugins {
  `kotlin-dsl`
  alias(libs.plugins.spotless)
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(17)
  }
}

spotless {
  kotlin {
    target("src/**/*.kt")
    ktlint()
//    licenseHeaderFile(rootProject.file("../../spotless/cb-copyright.txt"))
  }

  kotlinGradle {
    target("*.kts")
    ktlint()
//    licenseHeaderFile(rootProject.file("../../spotless/cb-copyright.txt"), "(^(?![\\/ ]\\**).*$)")
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.spotless.gradlePlugin)
  compileOnly(libs.compose.gradlePlugin)
  compileOnly(libs.composeCompiler.gradlePlugin)
  compileOnly(libs.licensee.gradlePlugin)
}

gradlePlugin {
  plugins {
    register("kotlinMultiplatform") {
      id = "dev.forcetower.instalytics.multiplatform"
      implementationClass = "dev.forcetower.instalytics.gradle.KotlinMultiplatformConventionPlugin"
    }
    register("androidLibrary") {
      id = "dev.forcetower.instalytics.android.library"
      implementationClass = "dev.forcetower.instalytics.gradle.AndroidLibraryConventionPlugin"
    }
  }
}

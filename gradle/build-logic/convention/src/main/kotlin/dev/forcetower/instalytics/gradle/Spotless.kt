package dev.forcetower.instalytics.gradle

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureSpotless() {
  // Skip Spotless for thirdparty code
  if (path.startsWith(":thirdparty")) return

  with(pluginManager) {
    apply("com.diffplug.spotless")
  }

  spotless {
    val ktlintVersion = libs.findLibrary("ktlint").get().get().version

    kotlin {
      target("src/**/*.kt")
      ktlint(ktlintVersion)
    }

    kotlinGradle {
      target("*.kts")
      ktlint(ktlintVersion)
    }
  }
}

private fun Project.spotless(action: SpotlessExtension.() -> Unit) = extensions.configure<SpotlessExtension>(action)

package dev.forcetower.instalytics.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon

@OptIn(ExperimentalKotlinGradlePluginApi::class)
class KotlinMultiplatformConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) = with(target) {
    with(pluginManager) {
      apply("org.jetbrains.kotlin.multiplatform")
    }

    extensions.configure<KotlinMultiplatformExtension> {
      applyDefaultHierarchyTemplate()

      jvm()
      if (pluginManager.hasPlugin("com.android.library")) {
        androidTarget()
      }

      // Function to check if we're on macOS with Apple Silicon (ARM)
      fun isMacOsArm(): Boolean {
        val osName = System.getProperty("os.name").lowercase()
        val osArch = System.getProperty("os.arch").lowercase()
        return osName.contains("mac") && (osArch.contains("aarch64") || osArch.contains("arm64"))
      }

      if (isMacOsArm()) {
        iosArm64()
        iosSimulatorArm64()
      }

      targets.withType<KotlinNativeTarget>().configureEach {
        binaries.configureEach {
          // Add linker flag for SQLite. See:
          linkerOpts("-lsqlite3")

          // Workaround for https://youtrack.jetbrains.com/issue/KT-64508
          freeCompilerArgs += "-Xdisable-phases=RemoveRedundantCallsToStaticInitializersPhase"
        }

        compilations.configureEach {
          compileTaskProvider.configure {
            compilerOptions {
              // Various opt-ins
              freeCompilerArgs.addAll(
                "-opt-in=kotlinx.cinterop.ExperimentalForeignApi",
                "-opt-in=kotlinx.cinterop.BetaInteropApi",
              )
            }
          }
        }
      }

      targets.configureEach {
        compilations.configureEach {
          compileTaskProvider.configure {
            compilerOptions {
              freeCompilerArgs.add("-Xexpect-actual-classes")
            }
          }
        }
      }

      metadata {
        compilations.configureEach {
          if (name == KotlinSourceSet.COMMON_MAIN_SOURCE_SET_NAME) {
            compileTaskProvider.configure {
              // We replace the default library names with something more unique (the project path).
              // This allows us to avoid the annoying issue of `duplicate library name: foo_commonMain`
              // https://youtrack.jetbrains.com/issue/KT-57914
              val projectPath = this@with.path.substring(1).replace(":", "_")
              this as KotlinCompileCommon
              moduleName.set("${projectPath}_commonMain")
            }
          }
        }
      }

      configureSpotless()
      configureKotlin()
    }
  }
}

fun Project.addKspDependencyForAllTargets(dependencyNotation: Any) = addKspDependencyForAllTargets("", dependencyNotation)
fun Project.addKspTestDependencyForAllTargets(dependencyNotation: Any) = addKspDependencyForAllTargets("Test", dependencyNotation)

private fun Project.addKspDependencyForAllTargets(
  configurationNameSuffix: String,
  dependencyNotation: Any,
) {
  val kmpExtension = extensions.getByType<KotlinMultiplatformExtension>()
  dependencies {
    kmpExtension.targets
      .asSequence()
      .filter { target ->
        // Don't add KSP for common target, only final platforms
        target.platformType != KotlinPlatformType.common
      }
      .forEach { target ->
        add(
          "ksp${target.targetName.toString().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }}$configurationNameSuffix",
          dependencyNotation,
        )
      }
  }
}

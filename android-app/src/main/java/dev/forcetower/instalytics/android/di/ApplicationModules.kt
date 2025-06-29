package dev.forcetower.instalytics.android.di

import dev.forcetower.instalytics.android.ui.di.PresentationModule
import dev.forcetower.instalytics.di.AndroidApplicationComponent

object ApplicationModules {
    val allModules = AndroidApplicationComponent().modules + PresentationModule.module
}
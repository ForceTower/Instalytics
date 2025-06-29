package dev.forcetower.instalytics.di

import co.touchlab.kermit.Logger
import dev.forcetower.instalytics.core.base.inject.ApplicationScope

abstract class IosApplicationComponent : SharedApplicationComponent {
    init { Logger.d { "Initializing main component" } }
    companion object
}
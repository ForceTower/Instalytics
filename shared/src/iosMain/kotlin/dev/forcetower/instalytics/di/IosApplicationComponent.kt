package dev.forcetower.instalytics.di

import co.touchlab.kermit.Logger

abstract class IosApplicationComponent : SharedApplicationComponent {
    init {
        Logger.d { "Initializing main component" }
    }

    companion object
}

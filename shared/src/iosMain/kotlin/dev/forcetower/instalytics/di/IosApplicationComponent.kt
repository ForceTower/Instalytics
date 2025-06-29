package dev.forcetower.instalytics.di

import co.touchlab.kermit.Logger

internal class IosApplicationComponent : SharedApplicationComponent {
    init {
        Logger.d { "Initializing main component" }
    }

    companion object
}

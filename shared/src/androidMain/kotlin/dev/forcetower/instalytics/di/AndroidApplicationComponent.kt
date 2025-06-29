package dev.forcetower.instalytics.di

import co.touchlab.kermit.Logger

class AndroidApplicationComponent : SharedApplicationComponent {
    init { Logger.d { "Initializing main component" } }
    companion object
}
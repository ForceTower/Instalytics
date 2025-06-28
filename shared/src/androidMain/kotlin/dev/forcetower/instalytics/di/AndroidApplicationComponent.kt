package dev.forcetower.instalytics.di

import android.content.Context
import co.touchlab.kermit.Logger
import dev.forcetower.instalytics.core.base.inject.ApplicationScope
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
@ApplicationScope
abstract class AndroidApplicationComponent(
    @get:Provides val context: Context
) : SharedApplicationComponent {
    init { Logger.d { "Initializing main component" } }
    companion object
}
package dev.forcetower.instalytics.di

import android.content.Context
import dev.forcetower.instalytics.core.base.inject.ApplicationScope
import dev.forcetower.kmm.toolkit.logdog.logdog
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
@ApplicationScope
abstract class AndroidApplicationComponent(
    @get:Provides val context: Context
) : SharedApplicationComponent {
    init { logdog { "Initializing main component" } }
    companion object
}
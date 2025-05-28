package dev.forcetower.instalytics.di

import dev.forcetower.instalytics.core.base.inject.ApplicationScope
import dev.forcetower.instalytics.data.storage.database.DarwinInstalyticsDatabaseBuilderFactory
import dev.forcetower.instalytics.data.storage.database.InstalyticsDatabaseBuilderFactory
import dev.forcetower.kmm.toolkit.logdog.LogdogLogger
import dev.forcetower.kmm.toolkit.logdog.logdog
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate
import me.tatarka.inject.annotations.Provides

@Component
@ApplicationScope
abstract class IosApplicationComponent : SharedApplicationComponent {
    init { logdog { "Initializing main component" } }
    companion object

    @Provides
    internal fun provideDatabase(): InstalyticsDatabaseBuilderFactory {
        return DarwinInstalyticsDatabaseBuilderFactory()
    }
}
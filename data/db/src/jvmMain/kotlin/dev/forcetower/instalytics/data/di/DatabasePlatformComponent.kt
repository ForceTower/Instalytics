package dev.forcetower.instalytics.data.di

import dev.forcetower.instalytics.core.base.inject.ApplicationScope
import dev.forcetower.instalytics.data.di.storage.database.JvmInstalyticsDatabaseBuilderFactory
import dev.forcetower.instalytics.data.storage.database.InstalyticsDatabaseBuilderFactory
import me.tatarka.inject.annotations.Provides

actual interface DatabasePlatformComponent {
    @Provides
    @ApplicationScope
    fun provideDatabaseFactory(): InstalyticsDatabaseBuilderFactory {
        return JvmInstalyticsDatabaseBuilderFactory()
    }
}
package dev.forcetower.instalytics.data.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.forcetower.instalytics.core.base.inject.ApplicationScope
import dev.forcetower.instalytics.data.storage.database.InstalyticsDB
import dev.forcetower.instalytics.data.storage.database.InstalyticsDatabaseBuilderFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import me.tatarka.inject.annotations.Provides

expect interface DatabasePlatformComponent

interface DatabaseComponent : DatabasePlatformComponent {
    @Provides
    @ApplicationScope
    fun provideDatabase(
        databaseFactory: InstalyticsDatabaseBuilderFactory,
    ): InstalyticsDB {
        return databaseFactory
            .create()
            .fallbackToDestructiveMigrationOnDowngrade(true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}
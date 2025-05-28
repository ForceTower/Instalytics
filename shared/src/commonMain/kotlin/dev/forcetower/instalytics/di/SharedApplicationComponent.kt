package dev.forcetower.instalytics.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.forcetower.instalytics.core.base.inject.ApplicationScope
import dev.forcetower.instalytics.data.storage.database.InstalyticsDB
import dev.forcetower.instalytics.data.storage.database.InstalyticsDatabaseBuilderFactory
import dev.forcetower.instalytics.domain.OtherTest
import dev.forcetower.instalytics.domain.ThingDomain
import kotlinx.coroutines.Dispatchers
import me.tatarka.inject.annotations.Provides

internal interface SharedApplicationComponent {
    val thing: ThingDomain
    val database: InstalyticsDB

    @Provides
    @ApplicationScope
    fun provideElement(): OtherTest {
        return OtherTest("Hey there world!")
    }

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
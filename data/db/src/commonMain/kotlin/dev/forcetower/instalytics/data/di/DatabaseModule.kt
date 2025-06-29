package dev.forcetower.instalytics.data.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.forcetower.instalytics.data.storage.database.InstalyticsDB
import dev.forcetower.instalytics.data.storage.database.InstalyticsDatabaseBuilderFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

object DatabaseModule {
    private val module = module {
        single<InstalyticsDB>(createdAtStart = true) {
            get<InstalyticsDatabaseBuilderFactory>()
                .create()
                .fallbackToDestructiveMigrationOnDowngrade(true)
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
    }

    private val additional = DatabasePlatformComponent.module()

    val modules = listOf(
        module,
        additional
    )
}
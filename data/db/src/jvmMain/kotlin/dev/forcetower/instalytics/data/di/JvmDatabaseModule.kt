package dev.forcetower.instalytics.data.di

import dev.forcetower.instalytics.data.di.storage.database.JvmInstalyticsDatabaseBuilderFactory
import dev.forcetower.instalytics.data.storage.database.InstalyticsDatabaseBuilderFactory
import org.koin.dsl.module

object JvmDatabaseModule {
    val module = module {
        single<InstalyticsDatabaseBuilderFactory> {
            JvmInstalyticsDatabaseBuilderFactory()
        }
    }
}
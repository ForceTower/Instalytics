package dev.forcetower.instalytics.data.di

import dev.forcetower.instalytics.data.storage.database.DarwinInstalyticsDatabaseBuilderFactory
import dev.forcetower.instalytics.data.storage.database.InstalyticsDatabaseBuilderFactory
import org.koin.dsl.module

object IosDatabaseModule {
    val module =
        module {
            single<InstalyticsDatabaseBuilderFactory> {
                DarwinInstalyticsDatabaseBuilderFactory()
            }
        }
}

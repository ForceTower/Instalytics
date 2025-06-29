package dev.forcetower.instalytics.data.di

import android.content.Context
import dev.forcetower.instalytics.data.storage.database.AndroidInstalyticsDatabaseBuilderFactory
import dev.forcetower.instalytics.data.storage.database.InstalyticsDatabaseBuilderFactory
import org.koin.dsl.module

object AndroidDatabaseModule {
    val module =
        module {
            single<InstalyticsDatabaseBuilderFactory> {
                AndroidInstalyticsDatabaseBuilderFactory(get<Context>())
            }
        }
}

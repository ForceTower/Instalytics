package dev.forcetower.instalytics.data.di

import android.content.Context
import dev.forcetower.instalytics.core.base.inject.ApplicationScope
import dev.forcetower.instalytics.data.storage.database.AndroidInstalyticsDatabaseBuilderFactory
import dev.forcetower.instalytics.data.storage.database.InstalyticsDatabaseBuilderFactory
import me.tatarka.inject.annotations.Provides

actual interface DatabasePlatformComponent {
    @Provides
    @ApplicationScope
    fun provideDatabaseFactory(
        context: Context
    ): InstalyticsDatabaseBuilderFactory {
        return AndroidInstalyticsDatabaseBuilderFactory(context)
    }
}
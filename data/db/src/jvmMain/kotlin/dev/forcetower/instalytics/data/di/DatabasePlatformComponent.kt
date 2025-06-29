package dev.forcetower.instalytics.data.di

import dev.forcetower.instalytics.core.base.koin.CreatesKoinModule

actual object DatabasePlatformComponent : CreatesKoinModule {
    actual override fun module() = JvmDatabaseModule.module
}
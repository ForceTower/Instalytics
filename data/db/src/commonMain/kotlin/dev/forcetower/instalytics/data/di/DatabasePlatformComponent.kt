package dev.forcetower.instalytics.data.di

import dev.forcetower.instalytics.core.base.koin.CreatesKoinModule
import org.koin.core.module.Module

expect object DatabasePlatformComponent : CreatesKoinModule {
    override fun module(): Module
}

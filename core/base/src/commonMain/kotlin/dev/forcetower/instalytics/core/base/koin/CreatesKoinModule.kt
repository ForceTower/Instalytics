package dev.forcetower.instalytics.core.base.koin

import org.koin.core.module.Module

interface CreatesKoinModule {
    fun module(): Module
}

package dev.forcetower.instalytics.di

import org.koin.core.context.startKoin

fun startKoin() {
    startKoin {
        modules(IosApplicationComponent().modules)
    }
}

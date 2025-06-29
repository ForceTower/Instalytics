package dev.forcetower.instalytics.domain.di

import dev.forcetower.instalytics.domain.usecase.FetchProfileUseCase
import org.koin.dsl.module

internal object DomainInternalModule {
    private val module = module {
        factory {
            FetchProfileUseCase(get())
        }
    }

    val modules = listOf(module)
}
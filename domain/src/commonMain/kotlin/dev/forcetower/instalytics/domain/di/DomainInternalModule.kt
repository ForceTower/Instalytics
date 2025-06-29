package dev.forcetower.instalytics.domain.di

import dev.forcetower.instalytics.domain.usecase.FetchConnectedUserProfileUseCase
import org.koin.dsl.module

internal object DomainInternalModule {
    private val module = module {
        factory {
            FetchConnectedUserProfileUseCase(get())
        }
    }

    val modules = listOf(module)
}

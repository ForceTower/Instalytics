package dev.forcetower.instalytics.domain.di

import dev.forcetower.instalytics.domain.usecase.FetchConnectedUserProfileUseCase
import dev.forcetower.instalytics.domain.usecase.GetInitialRouteUseCase
import org.koin.dsl.module

internal object DomainInternalModule {
    private val module = module {
        factory {
            FetchConnectedUserProfileUseCase(get())
        }

        factory {
            GetInitialRouteUseCase(get())
        }
    }

    val modules = listOf(module)
}

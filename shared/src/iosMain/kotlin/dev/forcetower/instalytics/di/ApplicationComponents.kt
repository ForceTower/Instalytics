package dev.forcetower.instalytics.di

import dev.forcetower.instalytics.domain.usecase.FetchConnectedUserProfileUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object ApplicationComponents : KoinComponent {
    val fetchConnectedUserProfile by inject<FetchConnectedUserProfileUseCase>()
}
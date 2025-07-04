package dev.forcetower.instalytics.domain.usecase

import dev.forcetower.instalytics.data.general.model.InitialAppRoute
import dev.forcetower.instalytics.data.general.repository.InitialChecksRepository
import dev.forcetower.instalytics.domain.model.AppInitialRoute

class GetInitialRouteUseCase(
    private val repository: InitialChecksRepository
) {
    suspend operator fun invoke(): AppInitialRoute {
        return when (repository.getInitialAppRoute()) {
            InitialAppRoute.Auth -> AppInitialRoute.Auth
            InitialAppRoute.Facebook -> AppInitialRoute.FacebookLink
            InitialAppRoute.Connected -> AppInitialRoute.Home
        }
    }
}
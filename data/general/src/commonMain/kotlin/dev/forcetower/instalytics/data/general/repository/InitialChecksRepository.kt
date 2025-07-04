package dev.forcetower.instalytics.data.general.repository

import dev.forcetower.instalytics.data.general.model.InitialAppRoute

interface InitialChecksRepository {
    suspend fun getInitialAppRoute(): InitialAppRoute
}
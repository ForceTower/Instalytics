package dev.forcetower.instalytics.data.general.repository

import dev.forcetower.instalytics.data.general.model.InitialAppRoute
import dev.forcetower.instalytics.data.storage.database.InstalyticsDB

internal class InitialChecksRepositoryImpl(
    private val instagram: InstalyticsDB
) : InitialChecksRepository {
    override suspend fun getInitialAppRoute(): InitialAppRoute {
        val facebook = instagram.facebookAccessToken.requireCurrent()
        if (facebook == null) return InitialAppRoute.Facebook
        return InitialAppRoute.Connected
    }
}
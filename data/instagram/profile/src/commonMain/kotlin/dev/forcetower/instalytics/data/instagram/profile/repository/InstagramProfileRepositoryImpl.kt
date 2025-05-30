package dev.forcetower.instalytics.data.instagram.profile.repository

import co.touchlab.kermit.Logger
import dev.forcetower.instalytics.data.instagram.profile.network.ProfileService
import dev.forcetower.instalytics.data.model.entity.InstagramAccount
import dev.forcetower.instalytics.data.storage.database.InstalyticsDB
import me.tatarka.inject.annotations.Inject

@Inject
class InstagramProfileRepositoryImpl internal constructor(
    private val database: InstalyticsDB,
    private val service: ProfileService
) : InstagramProfileRepository {
    override suspend fun me() {
        val accounts = service.accounts().data
        val business = accounts.firstNotNullOfOrNull { it.businessAccount }
        if (business == null) {
            Logger.d { "Did not find a business account. User didnt link thing..." }
            return
        }

        val id = business.id
        val instagram = service.profile(id)

        Logger.d { "Found instagram account: $instagram" }

        database.instagramAccount.insert(
            InstagramAccount(
                instagram.id,
                instagram.name,
                instagram.username,
                instagram.profilePictureUrl
            )
        )
    }
}
package dev.forcetower.instalytics.data.instagram.profile.repository

import co.touchlab.kermit.Logger
import dev.forcetower.instalytics.data.instagram.profile.network.ProfileService
import dev.forcetower.instalytics.data.model.entity.InstagramAccount
import dev.forcetower.instalytics.data.model.entity.InstagramMedia
import dev.forcetower.instalytics.data.model.entity.InstagramMediaChild
import dev.forcetower.instalytics.data.storage.database.InstalyticsDB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

internal class InstagramProfileRepositoryImpl(
    private val database: InstalyticsDB,
    private val service: ProfileService
) : InstagramProfileRepository {
    override fun me(): Flow<InstagramAccount> {
        return database.instagramAccount.me().filterNotNull()
    }

    override fun posts(): Flow<List<InstagramMedia>> {
        return database.instagramMedia.mine().map { it.map { el -> el.media } }
    }

    override suspend fun fetchMe() {
        val accounts = service.accounts().data
        val business = accounts.firstNotNullOfOrNull { it.businessAccount }
        if (business == null) {
            Logger.d { "Did not find a business account. User didnt link thing..." }
            return
        }

        val id = business.id
        val instagram = service.profile(id)

        database.instagramAccount.insert(
            InstagramAccount(
                instagram.id,
                instagram.name,
                instagram.username,
                instagram.profilePictureUrl,
                instagram.biography,
                instagram.followersCount,
                instagram.followsCount,
                instagram.mediaCount,
                true
            )
        )

        val medias = instagram.media
            .data
            .map {
                InstagramMedia(
                    it.id,
                    it.mediaType,
                    it.mediaUrl,
                    it.thumbnailUrl,
                    it.timestamp,
                    it.likeCount,
                    it.commentsCount,
                    it.caption,
                    instagram.id,
                )
            }

        database.instagramMedia.insertAll(medias)

        val children = instagram.media
            .data
            .filter { it.mediaType === "CAROUSEL_ALBUM" }
            .map { post ->
                post.children?.data?.map {
                    InstagramMediaChild(
                        it.id,
                        it.mediaType,
                        it.mediaUrl,
                        it.thumbnailUrl,
                        post.id
                    )
                } ?: emptyList()
            }
            .flatten()

        database.instagramMediaChildren.insertAll(children)
    }
}
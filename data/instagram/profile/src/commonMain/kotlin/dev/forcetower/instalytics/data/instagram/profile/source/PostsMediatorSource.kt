package dev.forcetower.instalytics.data.instagram.profile.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import dev.forcetower.instalytics.data.instagram.profile.network.ProfileService
import dev.forcetower.instalytics.data.model.composite.InstagramMediaWithChildren
import dev.forcetower.instalytics.data.model.entity.InstagramCursor
import dev.forcetower.instalytics.data.model.entity.InstagramMedia
import dev.forcetower.instalytics.data.model.entity.InstagramMediaChild
import dev.forcetower.instalytics.data.storage.database.InstalyticsDB

@OptIn(ExperimentalPagingApi::class)
internal class PostsMediatorSource(
    private val database: InstalyticsDB,
    private val network: ProfileService,
    private val profileId: String? = null
) : RemoteMediator<Int, InstagramMediaWithChildren>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, InstagramMediaWithChildren>
    ): MediatorResult {
        val profileId =
            profileId ?: database.instagramAccount
                .requireMe()
                ?.id ?: return MediatorResult.Success(endOfPaginationReached = true)

        val loadKey = when (loadType) {
            LoadType.REFRESH -> null
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                database.instagramCursor.query(lastItem.media.id, "MEDIA", profileId)?.after ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
        }

        val response = network.media(
            id = profileId,
            limit = state.config.pageSize,
            after = loadKey
        )

        if (loadType == LoadType.REFRESH) {
            database.instagramCursor.clearTypeOwner("MEDIA", profileId)
        }

        val medias = response
            .data
            ?.map {
                InstagramMedia(
                    it.id,
                    it.mediaType,
                    it.mediaUrl,
                    it.thumbnailUrl,
                    it.timestamp,
                    it.likeCount,
                    it.commentsCount,
                    it.caption,
                    profileId,
                )
            } ?: emptyList()

        database.instagramMedia.insertAll(medias)

        val children = response
            .data
            ?.filter { it.mediaType === "CAROUSEL_ALBUM" }
            ?.map { post ->
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
            ?.flatten() ?: emptyList()


        val after = response.paging?.cursors?.after
        val before = response.paging?.cursors?.before
        val items = response.data?.map {
            InstagramCursor(
                it.id,
                before,
                after,
                "MEDIA",
                profileId
            )
        } ?: emptyList()

        database.instagramMediaChildren.insertAll(children)
        database.instagramCursor.insertAll(items)

        return MediatorResult.Success(
            endOfPaginationReached = after == null
        )
    }
}
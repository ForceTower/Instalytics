package dev.forcetower.instalytics.data.instagram.profile.network

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import dev.forcetower.instalytics.data.instagram.profile.dto.AccountResponse
import dev.forcetower.instalytics.data.instagram.profile.dto.InstagramProfileDTO
import dev.forcetower.instalytics.data.instagram.profile.dto.MediaContainer
import dev.forcetower.instalytics.data.instagram.profile.dto.MediaItem

@Suppress("ktlint:standard:max-line-length")
internal interface ProfileService {
    @GET("me/accounts")
    suspend fun accounts(
        @Query("fields") fields: String = "instagram_business_account",
        @Query("access_token") token: String? = null
    ): AccountResponse

    @GET("{id}")
    suspend fun profile(
        @Path("id") id: String,
        @Query(
            "fields"
        ) fields: String = "biography,followers_count,follows_count,has_profile_pic,id,name,profile_picture_url,username,media_count,media{id,like_count,comments_count,media_type,media_url,thumbnail_url,timestamp,children{id,media_type,media_url,thumbnail_url}}"
    ): InstagramProfileDTO

    @GET("{id}/media")
    suspend fun media(
        @Path("id") id: String,
        @Query(
            "fields"
        ) fields: String = "id,caption,media_type,media_url,permalink,thumbnail_url,timestamp,like_count,comments_count,children{id,media_type,media_url,thumbnail_url}",
        @Query("limit") limit: Int = 20,
        @Query("after") after: String? = null
    ): MediaContainer

    @GET("{id}")
    suspend fun mediaDetails(
        @Path("id") id: String,
        @Query(
            "fields"
        ) fields: String = "id,caption,collaborators,media_type,media_url,permalink,thumbnail_url,timestamp,like_count,comments_count,children{id,media_type,media_url,thumbnail_url}"
    ): MediaItem
}

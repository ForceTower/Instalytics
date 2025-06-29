package dev.forcetower.instalytics.data.instagram.profile.network

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import dev.forcetower.instalytics.data.instagram.profile.dto.AccountResponse
import dev.forcetower.instalytics.data.instagram.profile.dto.InstagramProfile

internal interface ProfileService {
    @GET("me/accounts")
    suspend fun accounts(
        @Query("fields") fields: String = "instagram_business_account"
    ): AccountResponse

    @GET("{id}")
    suspend fun profile(
        @Path("id") id: String,
        @Query("fields") fields: String = "biography,followers_count,follows_count,has_profile_pic,id,name,profile_picture_url,username,media_count,media{id,media_type,media_url,thumbnail_url,timestamp}"
    ): InstagramProfile
}
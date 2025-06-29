package dev.forcetower.instalytics.data.instagram.profile.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class InstagramProfile(
    @SerialName("biography") val biography: String,
    @SerialName("followers_count") val followersCount: Int,
    @SerialName("follows_count") val followsCount: Int,
    @SerialName("has_profile_pic") val hasProfilePic: Boolean,
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("profile_picture_url") val profilePictureUrl: String?,
    @SerialName("username") val username: String,
    @SerialName("media_count") val mediaCount: Int,
    @SerialName("media") val media: MediaContainer
)
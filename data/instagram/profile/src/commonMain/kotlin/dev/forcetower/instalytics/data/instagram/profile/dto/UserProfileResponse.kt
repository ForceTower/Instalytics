package dev.forcetower.instalytics.data.instagram.profile.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InstagramProfile(
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

@Serializable
data class MediaContainer(
    @SerialName("data") val data: List<MediaItem>,
    @SerialName("paging") val paging: Paging? = null
)

@Serializable
data class MediaItem(
    @SerialName("id") val id: String,
    @SerialName("media_type") val mediaType: String,
    @SerialName("media_url") val mediaUrl: String,
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null,
    @SerialName("timestamp") val timestamp: String
)

@Serializable
data class Paging(
    @SerialName("cursors") val cursors: Cursors? = null,
    @SerialName("next") val next: String? = null
)

@Serializable
data class Cursors(
    @SerialName("before") val before: String? = null,
    @SerialName("after") val after: String? = null
)
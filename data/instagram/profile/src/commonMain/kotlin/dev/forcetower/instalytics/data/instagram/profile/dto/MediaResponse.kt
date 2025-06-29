package dev.forcetower.instalytics.data.instagram.profile.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MediaContainer(
    @SerialName("data") val data: List<MediaItem>? = null,
    @SerialName("paging") val paging: Paging? = null
)

@Serializable
internal data class MediaItem(
    @SerialName("id") val id: String,
    @SerialName("media_type") val mediaType: String,
    @SerialName("caption") val caption: String? = null,
    @SerialName("media_url") val mediaUrl: String,
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null,
    @SerialName("timestamp") val timestamp: String,
    @SerialName("like_count") val likeCount: Int,
    @SerialName("comments_count") val commentsCount: Int,
    @SerialName("children") val children: EdgeDataContainer<MediaItemChild>? = null,
    @SerialName("insights") val insights: EdgeDataContainer<MediaInsight>? = null
)

@Serializable
internal data class MediaItemChild(
    @SerialName("id") val id: String,
    @SerialName("media_type") val mediaType: String,
    @SerialName("media_url") val mediaUrl: String,
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null,
)

@Serializable
internal data class MediaInsight(
    @SerialName("name") val name: String,
    @SerialName("period") val period: String,
    @SerialName("values") val values: List<InsightValue>,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("id") val id: String
)

@Serializable
internal data class InsightValue(
    @SerialName("value") val value: Int
)

@Serializable
internal data class Paging(
    @SerialName("cursors") val cursors: Cursors? = null,
    @SerialName("next") val next: String? = null
)

@Serializable
internal data class Cursors(
    @SerialName("before") val before: String? = null,
    @SerialName("after") val after: String? = null
)
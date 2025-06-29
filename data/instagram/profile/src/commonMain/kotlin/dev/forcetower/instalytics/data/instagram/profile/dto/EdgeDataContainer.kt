package dev.forcetower.instalytics.data.instagram.profile.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class EdgeDataContainer<T>(
    @SerialName("data") val data: List<T>,
)
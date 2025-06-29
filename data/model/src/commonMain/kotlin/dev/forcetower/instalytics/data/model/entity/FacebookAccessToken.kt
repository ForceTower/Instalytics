package dev.forcetower.instalytics.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity
data class FacebookAccessToken(
    @PrimaryKey
    val id: String = Uuid.random().toString(),
    val accessToken: String,
    val active: Boolean
)

package dev.forcetower.instalytics.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InstagramAccount(
    @PrimaryKey
    val id: String,
    val name: String,
    val username: String,
    val profilePictureUrl: String?
)

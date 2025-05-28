package dev.forcetower.instalytics.data.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InstagramAccount(
    @PrimaryKey
    val id: String,
    val username: String,
    val name: String,
    val profilePictureUrl: String?
)

package dev.forcetower.instalytics.data.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(entity = InstagramAccount::class, parentColumns = ["id"], childColumns = ["owner"])
    ],
    indices = [
        Index("owner", unique = false)
    ]
)
data class InstagramMedia(
    @PrimaryKey
    val id: String,
    val type: String,
    val mediaUrl: String,
    val thumbnailUrl: String?,
    val timestamp: String,
    val likeCount: Int,
    val commentCount: Int,
    val caption: String?,
    val owner: String
)

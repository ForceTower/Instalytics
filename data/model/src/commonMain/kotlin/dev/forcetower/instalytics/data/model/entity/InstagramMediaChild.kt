package dev.forcetower.instalytics.data.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = InstagramMedia::class,
            parentColumns = ["id"],
            childColumns = ["parentId"]
        )
    ],
    indices = [
        Index("parentId", unique = false)
    ]
)
data class InstagramMediaChild(
    @PrimaryKey
    val id: String,
    val mediaType: String,
    val mediaUrl: String,
    val thumbnailUrl: String?,
    val parentId: String
)

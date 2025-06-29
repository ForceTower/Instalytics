package dev.forcetower.instalytics.data.model.entity

import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = ["id", "type"],
    indices = [
        Index("id", unique = false),
        Index("type", unique = false),
        Index("owner", unique = false),
    ]
)
data class InstagramCursor(
    val id: String,
    val before: String?,
    val after: String?,
    val type: String,
    val owner: String
)

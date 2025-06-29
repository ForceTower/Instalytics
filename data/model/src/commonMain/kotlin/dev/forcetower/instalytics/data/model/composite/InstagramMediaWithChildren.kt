package dev.forcetower.instalytics.data.model.composite

import androidx.room.Embedded
import androidx.room.Relation
import dev.forcetower.instalytics.data.model.entity.InstagramMedia
import dev.forcetower.instalytics.data.model.entity.InstagramMediaChild

data class InstagramMediaWithChildren(
    @Embedded
    val media: InstagramMedia,
    @Relation(parentColumn = "id", entityColumn = "parentId", entity = InstagramMediaChild::class)
    val children: List<InstagramMediaChild>
)
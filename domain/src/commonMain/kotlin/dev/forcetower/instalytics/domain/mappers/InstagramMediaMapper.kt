package dev.forcetower.instalytics.domain.mappers

import dev.forcetower.instalytics.data.model.entity.InstagramMedia
import dev.forcetower.instalytics.domain.model.InstagramPostUI

fun InstagramMedia.toDomain(): InstagramPostUI {
    return InstagramPostUI(
        id = id,
        imageUrl = thumbnailUrl ?: mediaUrl,
        likesCount = likeCount,
        commentsCount = commentCount
    )
}
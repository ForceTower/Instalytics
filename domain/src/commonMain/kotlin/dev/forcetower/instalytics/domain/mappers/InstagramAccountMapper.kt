package dev.forcetower.instalytics.domain.mappers

import dev.forcetower.instalytics.data.model.entity.InstagramAccount
import dev.forcetower.instalytics.domain.model.InstagramAccountUI

fun InstagramAccount.toDomain(): InstagramAccountUI = InstagramAccountUI(
    id = id,
    name = name,
    username = username,
    profilePictureUrl = profilePictureUrl,
    biography = biography,
    followers = followers,
    follows = follows,
    mediaCount = mediaCount
)

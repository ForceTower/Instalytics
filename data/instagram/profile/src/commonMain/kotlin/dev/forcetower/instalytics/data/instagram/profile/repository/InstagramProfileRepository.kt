package dev.forcetower.instalytics.data.instagram.profile.repository

import dev.forcetower.instalytics.data.model.entity.InstagramAccount
import kotlinx.coroutines.flow.Flow

interface InstagramProfileRepository {
    fun me(): Flow<InstagramAccount>
    suspend fun fetchMe()
}
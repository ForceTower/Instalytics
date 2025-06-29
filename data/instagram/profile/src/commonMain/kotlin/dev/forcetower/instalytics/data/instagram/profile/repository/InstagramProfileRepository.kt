package dev.forcetower.instalytics.data.instagram.profile.repository

import androidx.paging.PagingData
import dev.forcetower.instalytics.data.model.composite.InstagramMediaWithChildren
import dev.forcetower.instalytics.data.model.entity.InstagramAccount
import dev.forcetower.instalytics.data.model.entity.InstagramMedia
import kotlinx.coroutines.flow.Flow

interface InstagramProfileRepository {
    fun me(): Flow<InstagramAccount>

    fun posts(): Flow<List<InstagramMedia>>

    fun post(): Flow<PagingData<InstagramMediaWithChildren>>

    suspend fun fetchMe()
}

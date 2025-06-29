package dev.forcetower.instalytics.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import dev.forcetower.instalytics.data.instagram.profile.repository.InstagramProfileRepository
import dev.forcetower.instalytics.domain.mappers.toDomain
import dev.forcetower.instalytics.domain.model.InstagramAccountUI
import dev.forcetower.instalytics.domain.model.InstagramPostUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchConnectedUserProfileUseCase(private val repository: InstagramProfileRepository) {
    val me: Flow<InstagramAccountUI>
        get() = repository.me().map { it.toDomain() }

    val posts: Flow<List<InstagramPostUI>>
        get() = repository.posts().map { value -> value.map { it.toDomain() } }

    val post: Flow<PagingData<InstagramPostUI>>
        get() = repository.post().map { value -> value.map { el -> el.media.toDomain() } }

    @Throws(exceptionClasses = [IllegalStateException::class])
    suspend fun fetchMe() = repository.fetchMe()
}

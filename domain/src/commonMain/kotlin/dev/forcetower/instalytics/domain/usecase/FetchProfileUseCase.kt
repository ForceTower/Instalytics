package dev.forcetower.instalytics.domain.usecase

import dev.forcetower.instalytics.data.instagram.profile.repository.InstagramProfileRepository
import dev.forcetower.instalytics.domain.mappers.toDomain
import dev.forcetower.instalytics.domain.model.InstagramAccountUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchProfileUseCase(
    private val repository: InstagramProfileRepository
) {
    val me: Flow<InstagramAccountUI>
        get() = repository.me().map { it.toDomain() }

    suspend fun me() {
        return repository.fetchMe()
    }
}
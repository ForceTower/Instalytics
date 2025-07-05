package dev.forcetower.instalytics.domain.usecase

import dev.forcetower.instalytics.data.instagram.profile.repository.InstagramProfileRepository

class CheckFacebookLoginTokenUseCase(
    private val repository: InstagramProfileRepository
) {
    @Throws(exceptionClasses = [Exception::class])
    suspend fun checkTokenUsable(token: String): Boolean {
        return repository.setupToken(token)
    }
}
package dev.forcetower.instalytics.android.ui.navigation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import dev.forcetower.instalytics.domain.usecase.FetchProfileUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profile: FetchProfileUseCase
) : ViewModel() {
    val me = profile.me

    fun fetchProfile() {
        viewModelScope.launch {
            runCatching {
                profile.me()
            }.onFailure {
                Logger.e(it) { "Error fetching profile" }
            }
        }
    }
}
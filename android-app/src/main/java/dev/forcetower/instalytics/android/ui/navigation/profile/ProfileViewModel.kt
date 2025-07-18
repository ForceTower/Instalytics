package dev.forcetower.instalytics.android.ui.navigation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import co.touchlab.kermit.Logger
import dev.forcetower.instalytics.domain.model.InstagramPostUI
import dev.forcetower.instalytics.domain.usecase.FetchConnectedUserProfileUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profile: FetchConnectedUserProfileUseCase
) : ViewModel() {
    val me = profile.me
    val posts = profile.post.cachedIn(viewModelScope)

    fun fetchProfile() {
        viewModelScope.launch {
            runCatching {
                profile.fetchMe()
            }.onFailure {
                Logger.e(it) { "Error fetching profile" }
            }
        }
    }

    fun onPostClicked(post: InstagramPostUI) {
        Logger.d { "Clicked a post! $post" }
    }

    fun onOpenProfile() {
        Logger.d { "Clicked open profile" }
    }

    fun onShareProfile() {
        Logger.d { "Clicked share profile" }
    }
}
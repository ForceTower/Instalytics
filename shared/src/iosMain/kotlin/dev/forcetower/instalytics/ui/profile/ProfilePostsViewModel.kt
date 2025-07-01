package dev.forcetower.instalytics.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ItemSnapshotList
import androidx.paging.PagingData
import androidx.paging.PagingDataEvent
import androidx.paging.PagingDataPresenter
import androidx.paging.cachedIn
import dev.forcetower.instalytics.domain.model.InstagramPostUI
import dev.forcetower.instalytics.domain.usecase.FetchConnectedUserProfileUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.experimental.ExperimentalObjCName
import kotlin.experimental.ExperimentalObjCRefinement

@OptIn(ExperimentalObjCRefinement::class, ExperimentalObjCName::class)
open class ProfilePostsViewModel(
    fetchConnectedUserUseCase: FetchConnectedUserProfileUseCase
) : ViewModel() {
    @HiddenFromObjC
    private val posts: Flow<PagingData<InstagramPostUI>> = fetchConnectedUserUseCase.post
        .cachedIn(viewModelScope)

    private val postsPagingDataPresenter = object : PagingDataPresenter<InstagramPostUI>() {
        override suspend fun presentPagingDataEvent(event: PagingDataEvent<InstagramPostUI>) {
            updatePostsSnapshotList()
        }
    }

    @ObjCName("posts")
    val postsSnapshotList: MutableStateFlow<ItemSnapshotList<InstagramPostUI>> =
        MutableStateFlow(postsPagingDataPresenter.snapshot())

    init {
        viewModelScope.launch {
            posts.collectLatest {
                postsPagingDataPresenter.collectFrom(it)
            }
        }
    }

    private fun updatePostsSnapshotList() {
        postsSnapshotList.value = postsPagingDataPresenter.snapshot()
    }

    fun getElement(index: Int): InstagramPostUI? {
        return postsPagingDataPresenter[index]
    }
}

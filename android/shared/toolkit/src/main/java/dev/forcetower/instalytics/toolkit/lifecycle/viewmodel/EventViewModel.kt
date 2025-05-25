package dev.forcetower.instalytics.toolkit.lifecycle.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.forcetower.instalytics.toolkit.lifecycle.SingleLiveEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class EventViewModel<E : Any> : ViewModel() {
    private val channel = Channel<E>()
    val dispatcher: Flow<E> = channel.receiveAsFlow()

    private val _event = SingleLiveEvent<E>()
    val event: LiveData<E> = _event

    @MainThread
    fun sendEvent(producer: () -> E) {
        val value = producer()
        _event.value = value
        channel.trySend(value)
    }
}

package dev.forcetower.instalytics.toolkit.lifecycle.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class EventViewModel<E : Any> : ViewModel() {
    private val channel = Channel<E>()
    val events: Flow<E> = channel.receiveAsFlow()

    @MainThread
    fun sendEvent(producer: () -> E) {
        val value = producer()
        channel.trySend(value)
    }
}

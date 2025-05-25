package dev.forcetower.instalytics.toolkit.lifecycle.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<S : Any, E : Any>(initialState: S) : ViewModel() {
    var currentState: S = initialState
        private set

    private val channel = Channel<E>()
    val event: Flow<E> = channel.receiveAsFlow()

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    @MainThread
    fun sendEvent(producer: () -> E) {
        val value = producer()
        channel.trySend(value)
    }

    @MainThread
    fun setState(transform: (S) -> S) {
        currentState = transform(currentState)
        _state.value = currentState
    }
}

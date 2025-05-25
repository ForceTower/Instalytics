package dev.forcetower.instalytics.toolkit.lifecycle.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.forcetower.instalytics.toolkit.extensions.setValueIfNew
import dev.forcetower.instalytics.toolkit.lifecycle.SingleLiveEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<S : Any, E : Any>(initialState: S) : ViewModel() {
    var currentState: S = initialState
        private set

    private val channel = Channel<E>()
    val dispatcher: Flow<E> = channel.receiveAsFlow()

    private val _event = SingleLiveEvent<E>()
    val event: LiveData<E> = _event

    private val _stated = MutableStateFlow(initialState)
    val stated: StateFlow<S> = _stated

    private val _state = MutableLiveData(initialState)
    val state: LiveData<S> = _state

    @MainThread
    fun sendEvent(producer: () -> E) {
        val value = producer()
        _event.value = value
        channel.trySend(value)
    }

    @MainThread
    fun setState(transform: (S) -> S) {
        currentState = transform(currentState)
        _state.setValueIfNew(currentState)
        _stated.value = currentState
    }
}

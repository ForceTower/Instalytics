package dev.forcetower.instalytics.toolkit.lifecycle.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.forcetower.instalytics.toolkit.extensions.setValueIfNew
import dev.forcetower.instalytics.toolkit.lifecycle.SingleLiveEvent

abstract class BaseViewModel<S : Any, E : Any>(initialState: S) : ViewModel() {
    var currentState: S = initialState
        private set

    private val _event = SingleLiveEvent<E>()
    val event: LiveData<E> = _event

    private val _state = MutableLiveData(initialState)
    val state: LiveData<S> = _state

    @MainThread
    fun sendEvent(producer: () -> E) {
        _event.value = producer()
    }

    @MainThread
    fun setState(transform: (S) -> S) {
        currentState = transform(currentState)
        _state.setValueIfNew(currentState)
    }
}

package dev.forcetower.instalytics.toolkit.lifecycle.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class StateViewModel<S : Any>(initialState: S) : ViewModel() {
    var currentState: S = initialState
        private set

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    @MainThread
    fun setState(transform: (S) -> S) {
        currentState = transform(currentState)
        _state.value = currentState
    }
}

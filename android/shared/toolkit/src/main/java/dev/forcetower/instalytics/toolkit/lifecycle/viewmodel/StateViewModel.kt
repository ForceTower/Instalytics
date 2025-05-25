package dev.forcetower.instalytics.toolkit.lifecycle.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.forcetower.instalytics.toolkit.extensions.setValueIfNew

abstract class StateViewModel<S : Any>(initialState: S) : ViewModel() {
    var currentState: S = initialState
        private set

    private val _state = MutableLiveData(initialState)
    val state: LiveData<S> = _state

    @MainThread
    fun setState(transform: (S) -> S) {
        currentState = transform(currentState)
        _state.setValueIfNew(currentState)
    }
}

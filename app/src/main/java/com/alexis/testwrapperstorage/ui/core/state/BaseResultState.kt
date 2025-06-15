package com.alexis.testwrapperstorage.ui.core.state

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseResultState<T> : ViewModel() {
    protected val _state = MutableStateFlow<ResultState<T>>(ResultState.Loading)
    val state: StateFlow<ResultState<T>> = _state

    fun resetState() {
        _state.value = ResultState.Loading
    }
}
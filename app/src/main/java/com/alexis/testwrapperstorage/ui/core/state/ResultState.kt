package com.alexis.testwrapperstorage.ui.core.state

sealed class ResultState<out R> {
    data object Loading : ResultState<Nothing>()
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Failure(val exception: Throwable) : ResultState<Nothing>()
}
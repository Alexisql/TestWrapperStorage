package com.alexis.testwrapperstorage.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.alexis.testwrapperstorage.domain.repository.IStorageRepository
import com.alexis.testwrapperstorage.ui.core.state.BaseResultState
import com.alexis.testwrapperstorage.ui.core.state.ResultState
import com.alexis.wrapperstorage.domain.model.StorageKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storageManager: IStorageRepository,
    private val dispatcherIO: CoroutineDispatcher
) : BaseResultState<Unit>() {

    init {
        getLogin()
        getPreferencesByScreen()
    }

    private val _preferences = MutableStateFlow<List<Pair<String, *>>>(emptyList())
    val preferences: StateFlow<List<Pair<String, *>>> = _preferences.asStateFlow()

    private val _login = MutableStateFlow("")
    val login: StateFlow<String> = _login.asStateFlow()

    private fun getLogin() {
        viewModelScope.launch {
            val userKey = StorageKey<String>("Login", "LoginScreen", "Alexis")
            storageManager.getPreference(userKey, "")
                .onSuccess {
                    it.collect { preference ->
                        _login.value = preference
                    }
                }.onFailure {
                    Log.e("HomeViewModel-GetPreference", "${it.message}")
                    _state.value = ResultState.Failure(it)
                }
        }
    }

    fun <T> saveData(key: StorageKey<T>, value: T) {
        viewModelScope.launch {
            storageManager.savePreference(key, value)
                .onFailure {
                    Log.e("HomeViewModel-SavePreference", "${it.message}")
                    _state.value = ResultState.Failure(it)
                }
        }
    }

    fun removeData(key: String) {
        viewModelScope.launch {
            storageManager.deletePreference(key)
                .onFailure {
                    Log.e("HomeViewModel-DeletePreference", "${it.message}")
                    _state.value = ResultState.Failure(it)
                }
        }
    }

    private fun getPreferencesByScreen() {
        viewModelScope.launch {
            withContext(dispatcherIO) {
                storageManager.getPreferencesByScreen("HomeScreen")
            }.onSuccess {
                it.collect { preference ->
                    _preferences.value = preference.toList()
                }
            }.onFailure {
                Log.e("HomeViewModel-GetPreferenceByScreen", "${it.message}")
                _state.value = ResultState.Failure(it)
            }
        }
    }
}
package com.alexis.testwrapperstorage.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexis.testwrapperstorage.domain.repository.IStorageRepository
import com.alexis.wrapperstorage.domain.model.StorageKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storageManager: IStorageRepository,
    private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _preferences = MutableStateFlow<List<Pair<String, *>>>(emptyList())
    val preferences: StateFlow<List<Pair<String, *>>> = _preferences.asStateFlow()

    private val _login = MutableStateFlow("")
    val login: StateFlow<String> = _login.asStateFlow()

    fun getLogin() {
        viewModelScope.launch {
            val userKey = StorageKey<String>("Login", "LoginScreen", "Alexis")
            storageManager.getPreference(userKey, "")
                .flowOn(dispatcherIO)
                .collect { value ->
                    _login.value = value
                }
        }
    }

    fun <T> saveData(key: StorageKey<T>, value: T) {
        viewModelScope.launch {
            storageManager.savePreference(key, value)
        }
    }

    fun removeData(key: String) {
        viewModelScope.launch {
            storageManager.deletePreference(key)
        }
    }

    fun getPreferencesByScreen(screen: String) {
        viewModelScope.launch {
            val preferencesByScreen = storageManager.getPreferencesByScreen(screen)
            preferencesByScreen
                .flowOn(dispatcherIO)
                .collect {
                    _preferences.value = it.toMap().toList()
                }
        }
    }
}
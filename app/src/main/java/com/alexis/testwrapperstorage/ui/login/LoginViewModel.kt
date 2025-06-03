package com.alexis.testwrapperstorage.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexis.testwrapperstorage.domain.repository.IStorageRepository
import com.alexis.wrapperstorage.domain.model.StorageKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val storageManager: IStorageRepository
) : ViewModel() {

    fun <T> saveData(key: StorageKey<T>, value: T) {
        viewModelScope.launch {
            storageManager.savePreference(key, value)
        }
    }
}
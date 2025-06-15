package com.alexis.testwrapperstorage.ui.login

import androidx.lifecycle.viewModelScope
import com.alexis.testwrapperstorage.domain.repository.IStorageRepository
import com.alexis.testwrapperstorage.ui.core.state.BaseResultState
import com.alexis.testwrapperstorage.ui.core.state.ResultState
import com.alexis.wrapperstorage.domain.model.StorageKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val storageManager: IStorageRepository
) : BaseResultState<Unit>() {

    fun <T> saveData(key: StorageKey<T>, value: T) {
        viewModelScope.launch {
            storageManager.savePreference(key, value)
                .onSuccess { _state.value = ResultState.Success(it) }
                .onFailure { _state.value = ResultState.Failure(it) }
        }
    }

}
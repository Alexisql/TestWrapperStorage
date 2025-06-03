package com.alexis.testwrapperstorage.data.local

import com.alexis.testwrapperstorage.domain.repository.IStorageRepository
import com.alexis.wrapperstorage.domain.manager.impl.StorageManager
import com.alexis.wrapperstorage.domain.model.StorageKey
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storageManager: StorageManager
) : IStorageRepository {

    override suspend fun <T> savePreference(key: StorageKey<T>, value: T) {
        storageManager.put(key, value)
    }

    override fun <T> getPreference(key: StorageKey<T>, defaultValue: T): Flow<T> {
        return storageManager.get(key, defaultValue)
    }

    override suspend fun deletePreference(key: String) {
        storageManager.remove(key)
    }

    override fun getPreferencesByScreen(screen: String): Flow<Map<String, *>> {
        return storageManager.getPreferencesByScreen(screen)
    }
}
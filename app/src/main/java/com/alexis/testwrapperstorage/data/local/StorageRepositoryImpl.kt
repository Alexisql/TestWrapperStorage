package com.alexis.testwrapperstorage.data.local

import com.alexis.testwrapperstorage.domain.repository.IStorageRepository
import com.alexis.wrapperstorage.domain.exception.StorageException
import com.alexis.wrapperstorage.domain.manager.impl.StorageManager
import com.alexis.wrapperstorage.domain.model.StorageKey
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storageManager: StorageManager
) : IStorageRepository {

    override suspend fun <T> savePreference(key: StorageKey<T>, value: T): Result<Unit> {
        return try {
            val response = storageManager.put(key, value)
            Result.success(response)
        } catch (exception: StorageException.PutException) {
            Result.failure(exception)
        }
    }

    override fun <T> getPreference(key: StorageKey<T>, defaultValue: T): Result<Flow<T>> {
        return try {
            val response = storageManager.get(key, defaultValue)
            Result.success(response)
        } catch (exception: StorageException.GetException) {
            Result.failure(exception)
        }
    }

    override suspend fun deletePreference(key: String): Result<Unit> {
        return try {
            val response = storageManager.remove(key)
            Result.success(response)
        } catch (exception: StorageException.RemoveException) {
            Result.failure(exception)
        }
    }

    override fun getPreferencesByScreen(screen: String): Result<Flow<Map<String, *>>> {
        return try {
            val response = storageManager.getPreferencesByScreen(screen)
            Result.success(response)
        } catch (exception: StorageException.GetPreferencesException) {
            Result.failure(exception)
        }
    }
}
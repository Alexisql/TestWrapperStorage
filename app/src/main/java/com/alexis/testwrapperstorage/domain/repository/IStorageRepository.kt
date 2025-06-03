package com.alexis.testwrapperstorage.domain.repository

import com.alexis.wrapperstorage.domain.model.StorageKey
import kotlinx.coroutines.flow.Flow

interface IStorageRepository {
    suspend fun <T> savePreference(key: StorageKey<T>, value: T)
    fun <T> getPreference(key: StorageKey<T>, defaultValue: T): Flow<T>
    suspend fun deletePreference(key: String)
    fun getPreferencesByScreen(screen: String): Flow<Map<String, *>>
}
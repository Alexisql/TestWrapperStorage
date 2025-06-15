package com.alexis.testwrapperstorage.domain.repository

import com.alexis.wrapperstorage.domain.model.StorageKey
import kotlinx.coroutines.flow.Flow

interface IStorageRepository {
    suspend fun <T> savePreference(key: StorageKey<T>, value: T): Result<Unit>
    fun <T> getPreference(key: StorageKey<T>, defaultValue: T): Result<Flow<T>>
    suspend fun deletePreference(key: String): Result<Unit>
    fun getPreferencesByScreen(screen: String): Result<Flow<Map<String, *>>>
}
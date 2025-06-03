package com.alexis.testwrapperstorage.di

import com.alexis.testwrapperstorage.data.local.StorageRepositoryImpl
import com.alexis.testwrapperstorage.domain.repository.IStorageRepository
import com.alexis.wrapperstorage.di.Datastore
import com.alexis.wrapperstorage.domain.manager.impl.StorageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Singleton
    @Provides
    fun provideDispacher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun provideStorageRepository(@Datastore storageManager: StorageManager): IStorageRepository =
        StorageRepositoryImpl(storageManager)
}
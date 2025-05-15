package com.example.demoapplication.di

import com.example.demoapplication.data.LocalRepository
import com.example.demoapplication.localDatabase.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(itemDao: ItemDao) = LocalRepository(itemDao)
}
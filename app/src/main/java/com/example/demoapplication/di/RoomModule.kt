package com.example.demoapplication.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demoapplication.localDatabase.ApplicationDatabase
import com.example.demoapplication.localDatabase.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): ApplicationDatabase = synchronized(this) {
        Room.databaseBuilder(
            context = application.applicationContext,
            klass = ApplicationDatabase::class.java,
            name = "app-database"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(appDatabase: ApplicationDatabase): ItemDao = appDatabase.itemDao()
}
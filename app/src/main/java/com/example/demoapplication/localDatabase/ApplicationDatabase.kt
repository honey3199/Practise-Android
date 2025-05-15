package com.example.demoapplication.localDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1)
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao
}
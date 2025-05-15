package com.example.demoapplication.localDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(item: List<Item>)

    @Query("SELECT * FROM item")
    suspend fun getItems(): List<Item>
}
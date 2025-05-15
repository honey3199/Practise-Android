package com.example.demoapplication.localDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var itemID: Int,
    @ColumnInfo
    var itemName: String
)

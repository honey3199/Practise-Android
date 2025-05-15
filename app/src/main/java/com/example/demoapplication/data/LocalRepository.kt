package com.example.demoapplication.data

import com.example.demoapplication.localDatabase.Item
import com.example.demoapplication.localDatabase.ItemDao
import javax.inject.Inject

class LocalRepository @Inject constructor(private val itemDao: ItemDao) {
    suspend fun insertItems(items: List<Item>) = itemDao.insertItems(items)
}
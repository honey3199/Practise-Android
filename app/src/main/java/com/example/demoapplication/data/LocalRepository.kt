package com.example.demoapplication.data

import com.example.demoapplication.localDatabase.Item
import com.example.demoapplication.localDatabase.ItemDao
import com.example.demoapplication.networkClients.APIService
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val itemDao: ItemDao,
    private val apiService: APIService
) {
    suspend fun insertItems(items: List<Item>) = itemDao.insertItems(items)

    suspend fun getItemsFromRemote() = apiService.getItemsFromRemote()
}
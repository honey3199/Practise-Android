package com.example.demoapplication.data

import com.example.demoapplication.localDatabase.Item
import com.example.demoapplication.localDatabase.ItemDao
import com.example.demoapplication.networkClients.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val itemDao: ItemDao,
    private val apiService: APIService
) {
    suspend fun insertItems(items: List<Item>) = withContext(Dispatchers.IO) {
        itemDao.insertItems(items)
    }

    suspend fun getItems() =  withContext(Dispatchers.IO) { itemDao.getItems() }

    suspend fun getItemsFromRemote() = apiService.getItemsFromRemote()
}
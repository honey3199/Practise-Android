package com.example.demoapplication.ui.features.listView

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapplication.data.LocalRepository
import com.example.demoapplication.localDatabase.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListComposableViewModel @Inject constructor(private val repository: LocalRepository) :
    ViewModel() {

    val title: String = "H@nSh!!"
    private val TAG = "ListComposableViewModel"

    var remoteResponse by mutableStateOf<RemoteResponse>(RemoteResponse.Loading)
        private set

    private val _items = MutableLiveData<List<Item>>()
    var items: LiveData<List<Item>> = _items

    init {
        insertItems()
        remoteResponse = RemoteResponse.Loading
        fetchDetailsFromRemote()
    }

    private fun insertItems() = viewModelScope.launch {
        repository.insertItems(
            listOf(
                Item(itemID = 1, itemName = "item1"),
                Item(itemID = 2, itemName = "item2"),
                Item(itemID = 3, itemName = "item3"),
                Item(itemID = 4, itemName = "item4"),
                Item(itemID = 2, itemName = "item5"),
            )
        )

        _items.postValue(repository.getItems())
    }

    private fun fetchDetailsFromRemote() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = repository.getItemsFromRemote()
            if (response.isSuccessful) {
                Log.d(TAG, "fetchDetailsFromRemote: Success")
                Log.d(TAG, response.body().toString())
                remoteResponse = RemoteResponse.Error(response.body().toString())
            } else {
                Log.d(TAG, "fetchDetailsFromRemote: Failure")
                Log.d(TAG, response.errorBody().toString())
                remoteResponse = RemoteResponse.Error(response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.d(TAG, "fetchDetailsFromRemote: Exception")
            Log.d(TAG, e.printStackTrace().toString())
            remoteResponse = RemoteResponse.Error(e.printStackTrace().toString())
        }
    }
}

sealed interface RemoteResponse {
    data object Loading : RemoteResponse
    data class Error(val errorMessage: String) : RemoteResponse
    data class Success(val responseValue: String) : RemoteResponse
}
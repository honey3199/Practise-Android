package com.example.demoapplication.ui.features.listView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapplication.data.LocalRepository
import com.example.demoapplication.localDatabase.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListComposableViewModel @Inject constructor(private val repository: LocalRepository): ViewModel() {

    val title: String = "H@nSh!!"

    init {
        insertItems()
    }

    private fun insertItems() = viewModelScope.launch(Dispatchers.IO) {
        repository.insertItems(
            listOf(
                Item(itemID = 1, itemName = "item1"),
                Item(itemID = 2, itemName = "item2"),
                Item(itemID = 3, itemName = "item3"),
                Item(itemID = 4, itemName = "item4"),
                Item(itemID = 2, itemName = "item5"),
            )
        )
    }

}
package com.example.demoapplication.ui.features.listView

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListComposableViewModel @Inject constructor(): ViewModel() { /*private val networkRepository: NetworkRepository*/

    val title: String = "H@nSh!!"

//    fun getData() = viewModelScope.launch(Dispatchers.IO) {
//        val result = networkRepository.getDetails()
//    }

}
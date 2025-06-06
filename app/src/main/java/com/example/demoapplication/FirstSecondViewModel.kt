package com.example.demoapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstSecondViewModel @Inject constructor() : ViewModel() {

    private val _numbers = MutableStateFlow<List<Int>>(listOf())
    val numbers: StateFlow<List<Int>> = _numbers

    private val _shouldNavigate = MutableSharedFlow<Boolean>()
    val shouldNavigate: SharedFlow<Boolean> = _shouldNavigate

    private val _shouldNavigateState = MutableStateFlow<Boolean>(false)
    val shouldNavigateState: StateFlow<Boolean> = _shouldNavigateState

    private val _shouldNavigateLive = MutableLiveData<Boolean>(false)
    val shouldNavigateLive: LiveData<Boolean> = _shouldNavigateLive

    fun buttonClick() = viewModelScope.launch {
        for(i in 1..5) {
            Log.d("TAG", "shouldNavigate: $i")
            delay(1000)
        }
//        _shouldNavigate.emit(true)
//        _shouldNavigateState.emit(true)
        _shouldNavigateLive.postValue(true)
    }

    fun getStateFlow() = viewModelScope.launch {
        var current = 30
        while (current >= 0) {
            Log.w("111", "getStateFlow: Emit $current")
            val list = _numbers.value.toMutableList()
            list.add(current)
            _numbers.emit(list)
            delay(1000L)
            current--
        }
    }

    private fun getSharedFlow(): SharedFlow<Int> {
        val sharedFlow = MutableSharedFlow<Int>()
        viewModelScope.launch {
            var current = 5
            while (current >= 0) {
                Log.w("111", "getSharedFlow: Emit $current")
                sharedFlow.emit(current)
                delay(1000L)
                current--
            }
        }
        return sharedFlow
    }

    fun collectFlow() = viewModelScope.launch {
        val flow1 = getSharedFlow()
        launch {
            delay(2000)
            flow1.collect {
                Log.e("111", "collectFlow: $it")
            }
        }

        delay(3000)
        Log.d("111", "collectFlow() current flow  value : $flow1") // for StateFlow

        launch {
            delay(13000)
            flow1.collect {
                Log.d("111", "collectFlow: $it")
            }
        }
    }

    suspend fun fun1() {
        println("?????? Fun 1 start")
        delay(3000)
        println("?????? Fun 1 end")
    }

    suspend fun fun2() {
        println("?????? Fun 2 start")
        delay(1000)
        throw Exception("error")
        println("?????? Fun 2 end")
    }

    suspend fun fun3() {
        println("?????? Fun 3 start")
        delay(2000)
        println("?????? Fun 3 end")
    }
}
package com.example.myapplication

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.lang.Exception
import kotlin.system.measureTimeMillis

//If getUser fails throw an Exception
//If getuser success call get friendList and get comments parallely
//if getFriendlist or getComments take more then 1500ms / 1.5 sec then return emptylist for whichever API runs more then 1.5 sec
//if getFriend or getComments get fails return empty list

class AggregatedUserDataUsecase {

    suspend fun getAggregatedUserData(): AggregatedUserData {
        var userData = 0
        var friends = 0
        var comments = 0
        try {
            withContext(Dispatchers.IO) {
                userData = async {
                    getUser()
                }.await()

                launch {
                    launch {
                        friends = getFriendList()
                    }

                    launch {
                        comments = getComments()
                    }
                    throw kotlin.Exception("Completed")
                }

                launch {
                    delay(1500)
                    throw kotlin.Exception("TimeOut")
                }
            }
        } catch (e: kotlin.Exception) {
            Log.e("222", "exception ${e.message}")
        }

        return AggregatedUserData(userData, friends, comments)
    }

    private suspend fun getUser(): Int {
        delay(2000)
        return 45
    }

    private suspend fun getFriendList(): Int {
        delay(1000)
        return 65
    }

    private suspend fun getComments(): Int {
        delay(1000)
        return 75
    }
}


data class AggregatedUserData(
    var user: Int,
    var friends: Int,
    var comments: Int
)
package com.example.demoapplication.networkClients

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("photos")
    suspend fun getItemsFromRemote(): Response<JsonObject>
}
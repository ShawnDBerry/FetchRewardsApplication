package com.example.fetchrewardsapplication.network

import com.example.fetchrewardsapplication.model.Item
import retrofit2.Response
import retrofit2.http.GET

interface ItemsService {
    @GET("hiring.json")
    suspend fun getItems(): Response<ArrayList<Item>>
}
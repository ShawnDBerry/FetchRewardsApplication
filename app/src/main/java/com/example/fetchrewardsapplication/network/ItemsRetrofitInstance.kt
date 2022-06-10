package com.example.fetchrewardsapplication.network

import com.example.fetchrewardsapplication.model.Item
import com.example.fetchrewardsapplication.util.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemsRetrofitInstance {
    private var itemsService: ItemsService

    init {
        this.itemsService = createService(retrofitInstance())
    }

    private fun retrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun createService(retrofit: Retrofit): ItemsService {
        return retrofit.create(ItemsService::class.java)
    }
    suspend fun getItems(): Response<ArrayList<Item>> {
        return itemsService.getItems()
    }


}
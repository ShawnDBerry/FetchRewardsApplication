package com.example.fetchrewardsapplication.repository

import com.example.fetchrewardsapplication.network.ItemsRetrofitInstance

class ItemsRepository constructor(private val itemRetrofit: ItemsRetrofitInstance) {
    suspend fun getItems() = itemRetrofit.getItems()
}
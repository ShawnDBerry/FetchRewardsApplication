package com.example.fetchrewardsapplication.repository

import com.example.fetchrewardsapplication.network.ItemsService
import javax.inject.Inject

class ItemsRepository @Inject constructor(private val itemsService: ItemsService) {
    suspend fun getItems() = itemsService.getItems()
}
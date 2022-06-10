package com.example.fetchrewardsapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fetchrewardsapplication.repository.ItemsRepository

class ItemsViewModelFactory constructor(private val repository: ItemsRepository): ViewModelProvider.Factory
 {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
         return if (modelClass.isAssignableFrom(ItemsViewModel::class.java))
             ItemsViewModel(this.repository) as T
          else
             throw IllegalArgumentException("ViewModel Not Found")
     }
 }
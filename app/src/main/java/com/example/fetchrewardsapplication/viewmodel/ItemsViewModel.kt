package com.example.fetchrewardsapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fetchrewardsapplication.model.Item
import com.example.fetchrewardsapplication.network.ItemsRetrofitInstance
import com.example.fetchrewardsapplication.repository.ItemsRepository
import io.reactivex.internal.subscriptions.SubscriptionHelper.cancel
import kotlinx.coroutines.*
import okhttp3.Dispatcher

class ItemsViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {
    val itemsLiveData = MutableLiveData<ArrayList<Item>>()
    val noResultsLiveData = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    fun getItems() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = itemsRepository.getItems()
            val body = response.body()
            if (response.isSuccessful) {
                body?.filter { it.name != null || it.name != "" }
                body?.sortWith(compareBy({ it.name }, { it.listId }))
            withContext(Dispatchers.Main) {
                    loading.value = false
                    updateLiveData(body)
                }
            }
        }
    }

    private fun updateLiveData(list: ArrayList<Item>?) {
        if (list != null && list.size != 0) {
            itemsLiveData.value = (itemsLiveData.value?.apply {
                addAll(list)
            }) ?: list
        } else {
            noResults()
        }
    }

    private fun noResults() {
        noResultsLiveData.value = (noResultsLiveData.value)?.let {
            loading.value = false
            !it
        } ?: true
    }

    override fun onCleared() {
        super.onCleared()
        CoroutineScope(Dispatchers.Default).cancel()
    }
}
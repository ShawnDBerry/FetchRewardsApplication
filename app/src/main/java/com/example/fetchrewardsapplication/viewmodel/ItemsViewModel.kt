package com.example.fetchrewardsapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fetchrewardsapplication.model.Item
import com.example.fetchrewardsapplication.repository.ItemsRepository
import kotlinx.coroutines.*

class ItemsViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {
    val itemsLiveData = MutableLiveData<ArrayList<Item>>()
    val noResultsLiveData = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun getItems() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = itemsRepository.getItems()
            val body = response.body()
            /*body?.groupBy's not working for some weird reason but sortby
            * does the same operations*/
            body?.sortWith(compareBy({ it.listId }, { it.id }, {it.name}))
            /*body?.filter's  not working for some weird reason but sortby
            * does the same operation*/
            body?.removeAll { it.name == null || it.name == "" }
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    updateLiveData(body)
                    loading.value = false
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
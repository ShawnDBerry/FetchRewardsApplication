package com.example.fetchrewardsapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fetchrewardsapplication.model.Item
import com.example.fetchrewardsapplication.repository.ItemsRepository
import kotlinx.coroutines.*

class ItemsViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {
    val itemsLiveData = MutableLiveData<ArrayList<Item>>()
    val errorMessage = MutableLiveData<String>()
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getItems() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = itemsRepository.getItems()
            val body = response.body()
            /*body?.groupBy's not working for some weird reason but sortby
            * does the same operations*/
            body?.sortWith(compareBy({ it.listId }, { it.id }, { it.name }))
            /*body?.filter's  not working for some weird reason but sortby
            * does the same operation*/
            body?.removeAll { it.name == null || it.name == "" }
            if (response.isSuccessful)
            withContext(Dispatchers.Main) {
                updateLiveData(body)
            }
             else
                onError("Error : ${response.message()} ")
        }
    }
    private fun updateLiveData(list: ArrayList<Item>?) {
        if (list != null && list.size != 0)
            itemsLiveData.value = (itemsLiveData.value?.apply {
                addAll(list)
            }) ?: list
    }
    private fun onError(message: String) {
        errorMessage.value = message
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}


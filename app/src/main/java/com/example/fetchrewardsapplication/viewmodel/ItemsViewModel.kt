package com.example.fetchrewardsapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewardsapplication.model.Item
import com.example.fetchrewardsapplication.repository.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class ItemsViewModel @Inject constructor(private val itemsRepository: ItemsRepository) : ViewModel() {
    val itemsLiveData: LiveData<ArrayList<Item>>
    get() = itemsLiveData
    val errorMessage = MutableLiveData<String>()

    private val itemsLiveDataResponse = MutableLiveData<ArrayList<Item>?>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    init {
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
           itemsRepository.getItems().let { response ->

               if (response.isSuccessful) {
                   response.body()?.sortWith(compareBy({ it.listId }, { it.id }, { it.name }))
                   response.body()?.removeAll { it.name == null || it.name == "" }
                   withContext(Dispatchers.Main) { updateLiveData(response.body()) }
               }
               else withContext(Dispatchers.Main) { onError("Error : ${response.message()}") }
           }
        }
    }

    private fun updateLiveData(list: ArrayList<Item>?) {
        if (list != null && list.size != 0)
            itemsLiveDataResponse.postValue(list)
    }

    private fun onError(message: String) { errorMessage.value = message }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
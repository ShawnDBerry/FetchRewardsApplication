package com.example.fetchrewardsapplication.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fetchrewardsapplication.adapter.ItemsRVAdapter
import com.example.fetchrewardsapplication.databinding.ActivityMainBinding
import com.example.fetchrewardsapplication.model.Item
import com.example.fetchrewardsapplication.network.ItemsRetrofitInstance
import com.example.fetchrewardsapplication.repository.ItemsRepository
import com.example.fetchrewardsapplication.viewmodel.ItemsViewModel
import com.example.fetchrewardsapplication.viewmodel.ItemsViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var itemsViewModel: ItemsViewModel
    lateinit var itemsRVAdapter: ItemsRVAdapter
    private var items = ArrayList<Item>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = ItemsRetrofitInstance()
        val itemsRepository = ItemsRepository(retrofitService)
        itemsViewModel = ViewModelProvider(this, ItemsViewModelFactory(itemsRepository))
            .get(ItemsViewModel::class.java)

        itemsViewModel.itemsLiveData.observe(this) {
            items = it
            setUpRV(items)
        }
        itemsViewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        itemsViewModel.getItems()
    }
    private fun setUpRV(result: ArrayList<Item>) {
        itemsRVAdapter = ItemsRVAdapter(this, result)
        binding.itemsRecyclerview.adapter = itemsRVAdapter
    }
}


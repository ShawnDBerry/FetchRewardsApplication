package com.example.fetchrewardsapplication.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fetchrewardsapplication.adapter.ItemsRVAdapter
import com.example.fetchrewardsapplication.databinding.ActivityMainBinding
import com.example.fetchrewardsapplication.network.ItemsRetrofitInstance
import com.example.fetchrewardsapplication.repository.ItemsRepository
import com.example.fetchrewardsapplication.viewmodel.ItemsViewModel
import com.example.fetchrewardsapplication.viewmodel.ItemsViewModelFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemsViewModel =
            ViewModelProvider(this, ItemsViewModelFactory(ItemsRepository(ItemsRetrofitInstance())))
                .get(ItemsViewModel::class.java)

        itemsViewModel.itemsLiveData.observe(this) {
            binding.itemsRecyclerview.adapter = ItemsRVAdapter(it)
        }

        itemsViewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        itemsViewModel.getItems()
    }
}
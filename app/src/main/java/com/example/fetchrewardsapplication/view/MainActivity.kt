package com.example.fetchrewardsapplication.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fetchrewardsapplication.adapter.ItemsRVAdapter
import com.example.fetchrewardsapplication.databinding.ActivityMainBinding
import com.example.fetchrewardsapplication.viewmodel.ItemsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val viewModel: ItemsViewModel by viewModels()
//        val itemsAdapter = ItemsRVAdapter()
//
//        viewModel.itemsLiveData.observe(this) {
//            binding.itemsRecyclerview.adapter = itemsAdapter
//            if (it !== null)
//                itemsAdapter.submitList(it)
//        }
//
//        viewModel.errorMessage.observe(this) {
//            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
//        }
    }
}
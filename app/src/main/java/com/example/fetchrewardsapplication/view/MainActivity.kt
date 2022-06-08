package com.example.fetchrewardsapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardsapplication.R
import com.example.fetchrewardsapplication.adapter.ItemsRVAdapter
import com.example.fetchrewardsapplication.model.Item
import com.example.fetchrewardsapplication.network.ItemsRetrofitInstance
import com.example.fetchrewardsapplication.repository.ItemsRepository
import com.example.fetchrewardsapplication.viewmodel.ItemsViewModel
import com.example.fetchrewardsapplication.viewmodel.ItemsViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var itemsViewModel: ItemsViewModel
    lateinit var itemsRVAdapter: ItemsRVAdapter
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var recyclerView: RecyclerView? = null

    var items = ArrayList<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.items_recyclerview)
        val retrofitService = ItemsRetrofitInstance()
        val itemsRepository = ItemsRepository(retrofitService)
        itemsViewModel = ViewModelProvider(
            this,
            ItemsViewModelFactory(itemsRepository)
        ).get(ItemsViewModel::class.java)
        itemsViewModel.itemsLiveData.observe(this) { it ->
            items = it
            setUpRV(items)
            items.forEach{
                Log.d("TAG_Q", "item " + "listId: " + it.listId + " id: " +it.id + " name: " +it.name)
            }
        }
        itemsViewModel.noResultsLiveData.observe(this) {
            Toast.makeText(this, items.size, Toast.LENGTH_SHORT).show()
        }
        itemsViewModel.getItems()
    }

    private fun setUpRV(result: ArrayList<Item>) {
        itemsRVAdapter = ItemsRVAdapter(result)
        layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = itemsRVAdapter
        recyclerView?.layoutManager = layoutManager
        val decorater: RecyclerView.ItemDecoration =
            DividerItemDecoration(this, RecyclerView.VERTICAL)
        recyclerView?.addItemDecoration(decorater)

    }
}


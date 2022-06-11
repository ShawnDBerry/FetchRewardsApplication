package com.example.fetchrewardsapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardsapplication.R
import com.example.fetchrewardsapplication.databinding.ItemLayoutBinding
import com.example.fetchrewardsapplication.model.Item

class ItemsRVAdapter(private val applicationContext: Context, private var items: ArrayList<Item>) :
    RecyclerView.Adapter<ItemsRVAdapter.ItemsAdapterViewHolder>() {

    class ItemsAdapterViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapterViewHolder {
        return ItemsAdapterViewHolder(ItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: ItemsAdapterViewHolder, position: Int) {
        with(holder) {
            with(items[position]) {
                binding.id.text = applicationContext.getString(R.string.id, this.id.toString())
                binding.listId.text = applicationContext.getString(R.string.listId, this.listId.toString())
                binding.name.text = applicationContext.getString(R.string.name, this.name)
            }
        }
    }
    override fun getItemCount(): Int { return items.size }
}
package com.example.fetchrewardsapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardsapplication.databinding.ItemLayoutBinding
import com.example.fetchrewardsapplication.model.Item

class ItemsRVAdapter(private var items: ArrayList<Item>) :
    RecyclerView.Adapter<ItemsRVAdapter.ItemsAdapterViewHolder>() {

    class ItemsAdapterViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(item: Item) { binding.item = item }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapterViewHolder {
        return ItemsAdapterViewHolder(ItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemsAdapterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int { return items.size }
}
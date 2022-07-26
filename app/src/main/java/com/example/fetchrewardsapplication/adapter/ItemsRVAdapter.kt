package com.example.fetchrewardsapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardsapplication.databinding.ItemLayoutBinding
import com.example.fetchrewardsapplication.model.Item

class ItemsRVAdapter : RecyclerView.Adapter<ItemsRVAdapter.ItemsAdapterViewHolder>() {

   inner class ItemsAdapterViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(item: Item) { binding.item = item }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Item>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapterViewHolder {
        return ItemsAdapterViewHolder(ItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemsAdapterViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size
}
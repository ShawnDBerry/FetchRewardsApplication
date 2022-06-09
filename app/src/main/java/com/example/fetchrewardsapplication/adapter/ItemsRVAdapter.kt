package com.example.fetchrewardsapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardsapplication.R
import com.example.fetchrewardsapplication.model.Item

class ItemsRVAdapter(private var items: ArrayList<Item>) :
    RecyclerView.Adapter<ItemsRVAdapter.ItemsAdapterViewHolder>() {

    class ItemsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemId: TextView = itemView.findViewById(R.id.id)
        var listId: TextView = itemView.findViewById(R.id.listId)
        var itemName: TextView = itemView.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemsAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemsAdapterViewHolder, position: Int) {
        holder.itemId.text = "ID: " + items[position].id.toString()
        holder.listId.text = "List ID: " + items[position].listId.toString()
        holder.itemName.text = "Name: " + items[position].name
    }
}
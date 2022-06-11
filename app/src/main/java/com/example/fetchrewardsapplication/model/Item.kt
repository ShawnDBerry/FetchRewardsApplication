package com.example.fetchrewardsapplication.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Item {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("listId")
    @Expose
    var listId: Int? = null

    @SerializedName("name")
    @Expose
    val name: String? = null
}
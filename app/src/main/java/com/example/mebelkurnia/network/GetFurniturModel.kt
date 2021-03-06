package com.example.mebelkurnia.network

import com.google.gson.annotations.SerializedName

data class GetFurniturModel(
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("images")
    val images: String,
    @SerializedName("price")
    val price: String,
)


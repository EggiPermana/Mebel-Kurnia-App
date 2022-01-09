package com.example.mebelkurnia.network

import com.google.gson.annotations.SerializedName

data class GetFurnitureResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("data")
    val data: List<GetFurniturModel>
)

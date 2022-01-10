package com.example.mebelkurnia.network

import com.google.gson.annotations.SerializedName

data class CheckoutBody(
    @SerializedName("id_user")
    val idUser: Int,
    @SerializedName("id_furniture")
    val idFurniture: Int,
    val quantity: Int,
)

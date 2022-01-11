package com.example.mebelkurnia.network

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @Multipart
    @POST("api/v1/auth/login.php")
    fun login(
        @Part("username") name: RequestBody,
        @Part("password") password: RequestBody,
    ): Call<ResponLogin>

    @FormUrlEncoded
    @POST("api/v1/auth/register.php")
    fun register(
        @Field("username") name: String,
        @Field("password") password: String,
        @Field("handphone") no: Int
    ): Call<ResponRegister>

    @GET("api/v1/furniture/show.php")
    fun getFurniture(): Call<GetFurnitureResponse>

    @FormUrlEncoded
    @POST("api/v1/checkout/store.php")
    fun checkoutFurniture(
        @Field("id_user") idUser: Int,
        @Field("id_furniture") idFurniture: Int,
        @Field("quantity") no: Int,
    ): Call<ResponRegister>

    @FormUrlEncoded
    @POST("api/v1/order/store.php")
    fun orderFurniture(
        @Field("kode") kode: String,
        @Field("id_user") idUser: Int,
        @Field("id_furniture") idFurniture: Int,
        @Field("quantity") qty: Int,
        @Field("price") price: String,
        @Field("location") address: String
    ): Call<ResponRegister>


}
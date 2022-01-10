package com.example.mebelkurnia.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @POST("api/v1/auth/login.php")
    fun login(@Body request: LoginRequest): Call<ResponLogin>

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


}
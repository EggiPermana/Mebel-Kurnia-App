package com.example.mebelkurnia.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("api/v1/auth/login.php")
    fun login(@Body request: LoginRequest): Call<ResponLogin>

    @POST("api/v1/auth/register.php")
    fun register(@Body request: RegisterRequest): Call<ResponRegister>

}
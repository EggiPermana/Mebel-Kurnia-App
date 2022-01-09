package com.example.mebelkurnia.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApp {

    val baseurl = "http://kurnia-mebel.pancakara.com//"
    fun provideretrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun userService() = provideretrofit().create(UserService::class.java)

}
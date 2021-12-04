package com.example.mebelkurnia.network

import java.sql.Timestamp

data class RegisterRequest(
    val username: String,
    val password: String,
    val handphone: Int,
)
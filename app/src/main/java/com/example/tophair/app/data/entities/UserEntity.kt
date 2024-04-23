package com.example.tophair.app.data.entities

data class User(
    val userId: Number? = null,
    val nomeCompleto: String? = null,
    val email: String? = null,
    val token: String? = null,
    val senha: String? = null
)

data class UserLogin(
    val email: String,
    val senha: String
)
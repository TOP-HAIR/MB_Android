package com.example.tophair.app.data.api

import com.example.tophair.app.data.entities.User
import com.example.tophair.app.data.entities.UserLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {
    @POST("/usuarios/login")
    suspend fun postUserLogin(@Body userLogin: UserLogin): Response<User>
}
package com.example.tophair.app.data.api

import com.example.tophair.app.data.entities.User
import com.example.tophair.app.data.entities.UserLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @POST("/usuarios/login")
    suspend fun postUserLogin(@Body userLogin: UserLogin): Response<User>
    @GET("/usuarios/{userId}")
    suspend fun getUser(@Path("userId") userId: String): Response<User>

    @DELETE("/usuarios/{userId}")
    suspend fun deleteUser(@Path("userId") userId: String): Response<Unit>

    @PUT("/usuarios/{userId}")
    suspend fun updateUser(@Path("userId") userId: String, @Body updatedUser: User): Response<User>
}
package com.example.tophair.app.data.api

import com.example.tophair.app.data.entities.User
import com.example.tophair.app.data.entities.UserCadastroDeserealize
import com.example.tophair.app.data.entities.UserGet
import com.example.tophair.app.data.entities.UserLogin
import com.example.tophair.app.data.entities.UserUpdate
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {
    @POST("/api/usuarios/login")
    suspend fun postUserLogin(@Body userLogin: UserLogin): Response<User>

    @POST("/api/usuarios/cadastrar")
    suspend fun postUserCadastrar(@Body userCadastro: UserCadastroDeserealize): Response<Any>

    @GET("/api/usuarios/{id}")
    suspend fun getUser(@Path("id") userId: Int): Response<UserGet>

    @DELETE("/api/usuarios/{id}")
    suspend fun deleteUser(@Path("id") userId: Long): Response<Unit>

    @PUT("/api/usuarios/{id}")
    suspend fun updateUser(@Path("id") userId: Long, @Body updatedUser: UserUpdate): Response<User>

    @PUT("/api/usuarios/vincular-endereco/{idEndereco}/{idUsuario}")
    suspend fun updateVincularEnderecoUser(@Path("idEndereco") idEndereco: Long, @Path("idUsuario") idUsuario: Long): Response<User>
}
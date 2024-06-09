package com.example.tophair.app.data.api

import com.example.tophair.app.data.entities.Endereco
import com.example.tophair.app.data.entities.EnderecoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface EnderecoApi {
    @POST("/api/enderecos/cadastrar")
    suspend fun postEndereco(@Body endereco: Endereco): Response<EnderecoResponse>
}
package com.example.tophair.app.data.api

import com.example.tophair.app.data.entities.CepResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepApi {
    @GET("{cep}/json/")
    suspend fun getCepInfo(@Path("cep") cep: String): CepResponse
}
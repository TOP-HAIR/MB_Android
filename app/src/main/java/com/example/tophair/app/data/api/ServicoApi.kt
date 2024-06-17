package com.example.tophair.app.data.api

import com.example.tophair.app.data.entities.Servico
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServicoApi {
    @GET("/api/servicos/empresa/{id}")
    suspend fun getListaServicoEmpresa(@Path("id") empresaId: Int): Response<List<Servico>>

    @GET("/api/servicos/{id}")
    suspend fun getServicoEmpresa(@Path("id") idServico: Int): Response<Servico>
}
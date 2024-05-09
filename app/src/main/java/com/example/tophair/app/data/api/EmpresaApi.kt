package com.example.tophair.app.data.api

import com.example.tophair.app.data.entities.Empresa
import com.example.tophair.app.data.entities.EmpresaPorId
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EmpresaApi {

    @GET("/api/empresas/top5-empresas/{id}")
    suspend fun getTop5MelhoresEmpresas(@Path("id") userId: Int): Response<List<Empresa>>

    @GET("/api/empresas/{id}")
    suspend fun getEmpresaPeloId(@Path("id") empresaId: Int?): Response<EmpresaPorId>

    @GET("/api/empresas/filtro-empresas/{id}")
    suspend fun getFiltroEmpresas(@Path("id") userId: Int, @Query("estado") estado: String, @Query("nomeServico") nomeServico: String, @Query("nomeEmpresa") nomeEmpresa: String): Response<List<Empresa>>
}
package com.example.tophair.app.data.api

import com.example.tophair.app.data.entities.Agenda
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AgendaApi {
    @GET("/api/agendas/usuario/{id}")
    suspend fun getAgendaUser(@Path("id") userId: Int): Response<List<Agenda>>

}
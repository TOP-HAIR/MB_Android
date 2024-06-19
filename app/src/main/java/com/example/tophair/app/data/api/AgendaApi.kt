package com.example.tophair.app.data.api

import com.example.tophair.app.data.entities.AgendaCancelado
import com.example.tophair.app.data.entities.AgendaEmpresa
import com.example.tophair.app.data.entities.AgendaEmpresaDto
import com.example.tophair.app.data.entities.AgendaPost
import com.example.tophair.app.data.entities.AgendaResponse
import com.example.tophair.app.data.entities.AgendaServico
import com.example.tophair.app.data.entities.UsuarioAgenda
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AgendaApi {

    @POST("/api/agendas")
    suspend fun postAgenda(@Body agenda: AgendaPost, @Query("idEmpresa") idEmpresa: Int?, @Query("idUsuario") idUsuario: Int?, @Query("idServico") idServico: Int?): Response<AgendaResponse>

    @GET("/api/agendas/usuario/{id}")
    suspend fun getAgendaUser(@Path("id") userId: Int): Response<List<AgendaEmpresaDto>>

    @PUT("/api/agendas/vincular-usuario/{idAgenda}/{idUsuario}")
    suspend fun putAgendaVincularUsuario(
        @Path("idAgenda") idAgenda: Int?,
        @Path("idUsuario") idUsuario: Int?
    ): Response<UsuarioAgenda>

    @PUT("/api/agendas/vincular-servico/{idAgenda}/{idServico}")
    suspend fun putAgendaVincularServico(
        @Path("idAgenda") idAgenda: Int?,
        @Path("idServico") idServico: Int?
    ): Response<AgendaServico>

    @PUT("/api/agendas/vincular-empresa/{idAgenda}/{idEmpresa}")
    suspend fun putAgendaVincularEmpresa(
        @Path("idAgenda") idAgenda: Int?,
        @Path("idEmpresa") idEmpresa: Int?
    ): Response<AgendaEmpresa>

    @PUT("/api/agendas/cancelar-agendamento/{idAgenda}")
    suspend fun putCancelarAgenda(
        @Path("idAgenda") idAgenda: Int?
    ): Response<AgendaCancelado>
}
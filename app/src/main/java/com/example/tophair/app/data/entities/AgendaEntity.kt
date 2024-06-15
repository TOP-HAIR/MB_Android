package com.example.tophair.app.data.entities

import java.io.Serializable
import kotlinx.serialization.SerializationStrategy
import java.time.LocalDateTime

data class Agenda(
    val idAgenda: Int? = null,
    val start: LocalDateTime? = null,
    val end: LocalDateTime? = null,
    val status: String? = null,
    val empresaDto: EmpresaDto? = null
)

data class AgendaData(
    val idAgenda: Int? = null,
) : Serializable {
    companion object {
        fun serializer(): SerializationStrategy<AgendaData> {
            TODO("Not yet implemented")
        }
    }
}
package com.example.tophair.app.data.entities

import java.time.LocalDateTime

data class Agenda(
    val idAgenda: Int? = null,
    val start: LocalDateTime? = null,
    val end: LocalDateTime? = null,
    val title: String? = null,
    val empresaDto: EmpresaDto? = null
)
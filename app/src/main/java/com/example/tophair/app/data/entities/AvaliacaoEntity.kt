package com.example.tophair.app.data.entities

data class Avaliacao(
    val idAvaliacao: Int? = null,
    val nivel: Int? = null,
    val comentario: String? = null,
    val usuario: Usuario? = null,
    val empresaDto: EmpresaDto? = null
)

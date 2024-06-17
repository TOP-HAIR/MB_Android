package com.example.tophair.app.data.entities

import kotlinx.serialization.SerializationStrategy
import java.io.Serializable
import java.time.LocalDateTime

data class AgendaPost(
    val startTime: String? = null,
    val endTime: String? = null,
    val background: String? = null,
    val title: String? = null,
)

data class AgendaResponse(
    val idAgenda: Int? = null,
    val start: String? = null,
    val end: String? = null,
    val status: String? = null,
)

data class AgendaEmpresaDto(
    val idAgenda: Int? = null,
    val start: String? = null,
    val end: String? = null,
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

data class UsuarioAgenda(
    val idUsuario: Int,
    val cpf: String,
    val nomeCompleto: String,
    val email: String,
    val senha: String,
    val telefone: String,
    val isProfissional: Boolean,
    val agenda: AgendaUsuario
)

data class AgendaUsuario(
    val idAgenda: Int,
    val start: String,
    val end: String,
    val title: String
)

data class AgendaServico(
    val idAgendaServico: Int,
    val agenda: AgendaUsuario,
    val servico: ServicoAgenda
)

data class ServicoAgenda(
    val idServico: Int,
    val nomeServico: String,
    val categoria: String,
    val descricao: String,
    val preco: Double,
    val qtdTempoServico: String
)

data class AgendaEmpresa(
    val idAgenda: Int?,
    val start: String?,
    val end: String?,
    val title: String?,
    val empresa: EmpresaAgenda?
)

data class EmpresaAgenda(
    val idEmpresa: Int?,
    val razaoSocial: String?,
    val cnpj: String?
)
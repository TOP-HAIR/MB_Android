package com.example.tophair.app.data.entities

data class Empresa(
    val idEmpresa: Int? = null,
    val razaoSocial: String? = null,
    val endereco: Endereco? = null,
    val avaliacoes: List<Avaliacao>? = null,
    val mediaNivelAvaliacoes: Int? = null,
    val arquivoDtos: List<ArquivoDto>? = null
)
data class EmpresaPorId(
    val idEmpresa: Int? = null,
    val razaoSocial: String? = null,
    val cnpj: String? = null,
    val arquivos: List<ArquivoDto>? = null
)

data class EmpresaDto(
    val idEmpresa: Int? = null,
    val razaoSocial: String? = null,
    val cnpj: String? = null
)

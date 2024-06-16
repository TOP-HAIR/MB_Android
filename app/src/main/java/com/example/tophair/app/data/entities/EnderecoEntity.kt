package com.example.tophair.app.data.entities

import java.io.Serializable

data class Endereco(
    val logradouro: String? = null,
    val bairro: String? = null,
    val numero: Int? = null,
    val estado: String? = null,
    val complemento: String? = null,
    val cidade: String? = null,
    val cep: String? = null
)

data class CepResponse(
    val cep: String,
    val logradouro: String,
    val complemento: String?,
    val bairro: String,
    val localidade: String,
    val uf: String
)

data class EnderecoResponse(
    val idEndereco: Integer? = null,
    val logradouro: String? = null,
    val bairro: String? = null,
    val numero: Int? = null,
    val estado: String? = null,
    val complemento: String? = null,
    val cidade: String? = null,
    val cep: String? = null
)

data class EnderecoSerializable(
    val logradouro: String? = null,
    val bairro: String? = null,
    val numero: String? = null,
    val estado: String? = null,
    val complemento: String? = null,
    val cidade: String? = null,
    val cep: String? = null
) : Serializable
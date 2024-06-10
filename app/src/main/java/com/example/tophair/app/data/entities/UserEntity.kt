package com.example.tophair.app.data.entities

import java.io.Serializable

data class User(
    val userId: Number? = null,
    val nomeCompleto: String? = null,
    val email: String? = null,
    val token: String? = null
)

data class Usuario(
    val idUsuario: Integer? = null,
    val cpf: String? = null,
    val nomeCompleto: String? = null,
    val email: String? = null,
    val telefone: String? = null,
    val isProfissional: Boolean? = null
)

data class UserLogin(
    val email: String? = null,
    val senha: String? = null
)

data class UserCadastro(
    var cpf: String? = null,
    var nomeCompleto: String? = null,
    var email: String? = null,
    var senha: String? = null,
    var telefone: String? = null,
    var profissional: Boolean? = false
) : Serializable

data class UserCadastroDeserealize(
    var cpf: String? = null,
    var nomeCompleto: String? = null,
    var email: String? = null,
    var senha: String? = null,
    var telefone: String? = null,
    var profissional: Boolean? = false
)

data class UserUpdate(
    var nomeCompleto: String? = null,
    var email: String? = null,
    var telefone: String? = null,
    var profissional: Boolean? = false
)

data class UserGet(
    val userId: Number? = null,
    var cpf: String? = null,
    var nomeCompleto: String? = null,
    var email: String? = null,
    var telefone: String? = null,
    var isProfissional: Boolean? = false,
    var arquivo: String? = null,
)
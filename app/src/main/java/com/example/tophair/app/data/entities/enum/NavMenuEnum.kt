package com.example.tophair.app.data.entities.enum

import com.example.tophair.R

enum class NavMenuEnum(val descricao: String,val nom_rota: String, val imagem: Int) {
    HOME("Tela Inicial","Início", R.mipmap.icon_home),
    SEARCH("Tela de Busca", "Buscar", R.mipmap.icon_search),
    CALENDAR("Tela Agendamento","Agenda", R.mipmap.icon_calendar),
    USER("Tela do Usuário","Perfil", R.mipmap.icon_user_perfil)
}
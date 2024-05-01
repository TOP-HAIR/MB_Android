package com.example.tophair.app.data.entities.enum

import com.example.tophair.R

enum class FilterServicoEnum(
        val imagemFiltro: Int,
        val descricaoFiltro: String,
        val textoFiltro: Int,
        val onClick: () -> Unit
    ) {
        TINTURA_PARA_CABELO(
            imagemFiltro = R.mipmap.icon_tintura_para_cabelo,
            descricaoFiltro = "Serviço de pintura de cabelo",
            textoFiltro = R.string.txt_tintura,
            onClick = {}
        ),
        CABELO_MASCULINO_CURTO(
            imagemFiltro = R.mipmap.icon_cabelo_masculino_curto,
            descricaoFiltro = "Cabelo Masculino",
            textoFiltro = R.string.txt_cabelo_masculino,
            onClick = {  }
        ),
        HOMEM_COM_BARBA(
            imagemFiltro = R.mipmap.icon_homem_com_barba,
            descricaoFiltro = "Barba de Homem",
            textoFiltro = R.string.txt_barba,
            onClick = {  }
        )
    }
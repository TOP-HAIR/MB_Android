package com.example.tophair.app.data.entities.enum

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

enum class TitleType(val tipoTitulo: String, val fontSize: TextUnit) {
    H1(tipoTitulo = "h1", fontSize = 22.sp),
    H2(tipoTitulo = "h2", fontSize = 20.sp),
    H3(tipoTitulo = "h3", fontSize = 18.sp),
    H4(tipoTitulo = "h4", fontSize = 16.sp),
    H5(tipoTitulo = "h5", fontSize = 14.sp),
    H6(tipoTitulo = "h6", fontSize = 12.sp)
}
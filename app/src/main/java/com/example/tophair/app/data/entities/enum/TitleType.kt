package com.example.tophair.app.data.entities.enum

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

enum class TitleType(val tipoTitulo: String,val fontSize: TextUnit) {
    H1(tipoTitulo = "h1", 24.sp),
    H2(tipoTitulo = "h2", 22.sp),
    H3(tipoTitulo = "h3", 20.sp),
    H4(tipoTitulo = "h4", 18.sp),
    H5(tipoTitulo = "h5", 12.sp),
    H6(tipoTitulo = "h6", 14.sp)
}
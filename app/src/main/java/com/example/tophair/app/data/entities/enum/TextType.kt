package com.example.tophair.app.data.entities.enum

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

enum class TextType(val textType: String, val fontSize: TextUnit) {
    EXTRA_SMALL(textType = "EXTRA_SMALL", fontSize = 10.sp),
    SMALL(textType = "SMALL", fontSize = 12.sp),
    MEDIUM(textType = "MEDIUM", fontSize = 14.sp),
    LARGE(textType = "LARGE", fontSize = 16.sp),
    EXTRA_LARGE(textType = "EXTRA_LARGE", fontSize = 18.sp),
    ULTRA_EXTRA_LARGE(textType = "EXTRA_LARGE", fontSize = 26.sp),
}
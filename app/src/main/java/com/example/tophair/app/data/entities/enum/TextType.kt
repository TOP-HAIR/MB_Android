package com.example.tophair.app.data.entities.enum

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

enum class TextType(val textType: String, val fontSize: TextUnit) {
    EXTRA_SMALL("EXTRA_SMALL", 10.sp),
    SMALL("SMALL", 12.sp),
    MEDIUM("MEDIUM", 14.sp),
    LARGE("LARGE", 16.sp),
    EXTRA_LARGE("EXTRA_LARGE", 18.sp)
}
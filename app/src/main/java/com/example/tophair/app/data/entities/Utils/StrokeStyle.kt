package com.example.tophair.app.data.entities.Utils

import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class StrokeStyle(
    val width: Dp = 4.dp,
    val strokeCap: StrokeCap = StrokeCap.Round,
    val glowRadius: Dp? = 4.dp
)
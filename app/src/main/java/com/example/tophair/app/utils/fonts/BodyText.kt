package com.example.tophair.app.utils.fonts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.tophair.R
import com.example.tophair.app.data.entities.enum.TextType

@Composable
fun TextComposable(
    textTitle: String,
    typeTitle: TextType,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    fontFamily: FontFamily = FontFamily(
        Font(R.font.poppins_medium)
    )
) {
    Text(
        text = textTitle,
        modifier = modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = typeTitle.fontSize,
            color = Color.White,
            fontWeight = fontWeight,
            textAlign = textAlign,
            fontFamily = fontFamily
        )
    )
}
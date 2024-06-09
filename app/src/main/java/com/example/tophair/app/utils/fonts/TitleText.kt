package com.example.tophair.app.utils.fonts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.tophair.R
import com.example.tophair.app.data.entities.enum.TitleType
import com.example.tophair.ui.theme.TopHairTheme

@Composable
fun TitleComposable(
    textTitle: String,
    typeTitle: TitleType,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    fontFamily: FontFamily = FontFamily(
        Font(R.font.poppins_medium)
    )
) {
    val fontSize = when (typeTitle.tipoTitulo) {
        "h1" -> 22.sp
        "h2" -> 20.sp
        "h3" -> 18.sp
        "h4" -> 16.sp
        "h5" -> 14.sp
        "h6" -> 12.sp
        else -> 14.sp
    }

    Text(
        text = textTitle,
        modifier = modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = fontSize,
            color = Color.White,
            fontWeight = fontWeight,
            textAlign = textAlign,
            fontFamily = fontFamily
        )
    )
}

@Preview
@Composable
fun TitleComposablePreview() {
    TopHairTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            TitleComposable(
                typeTitle = TitleType.H1,
                textTitle = stringResource(R.string.lorem_ipsum).toUpperCase(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TitleComposable(
                typeTitle = TitleType.H2,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TitleComposable(
                typeTitle = TitleType.H3,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TitleComposable(
                typeTitle = TitleType.H4,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TitleComposable(
                typeTitle = TitleType.H5,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TitleComposable(
                typeTitle = TitleType.H6,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}


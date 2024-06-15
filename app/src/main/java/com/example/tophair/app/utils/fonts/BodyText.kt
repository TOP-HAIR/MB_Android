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
import com.example.tophair.R
import com.example.tophair.app.data.entities.enum.TextType
import com.example.tophair.ui.theme.TopHairTheme

@Composable
fun TextComposable(
    textTitle: String,
    typeTitle: TextType,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    textColor: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start,
    fontFamily: FontFamily = FontFamily(
        Font(R.font.poppins_medium)
    )
) {
    Text(
        text = textTitle,
        modifier = modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = typeTitle.fontSize,
            color = textColor,
            fontWeight = fontWeight,
            textAlign = textAlign,
            fontFamily = fontFamily
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TextComposablePreview() {
    TopHairTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            TextComposable(
                typeTitle = TextType.EXTRA_LARGE,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TextComposable(
                typeTitle = TextType.LARGE,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TextComposable(
                typeTitle = TextType.MEDIUM,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TextComposable(
                typeTitle = TextType.SMALL,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TextComposable(
                typeTitle = TextType.EXTRA_SMALL,
                textTitle = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}
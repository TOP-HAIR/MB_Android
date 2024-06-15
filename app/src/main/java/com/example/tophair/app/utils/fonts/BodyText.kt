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
    textBody: String,
    typeText: TextType,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    textColor: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start,
    fontFamily: FontFamily = FontFamily(
        Font(R.font.poppins_medium)
    )
) {
    Text(
        text = textBody,
        modifier = modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = typeText.fontSize,
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
                typeText = TextType.EXTRA_LARGE,
                textBody = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TextComposable(
                typeText = TextType.LARGE,
                textBody = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TextComposable(
                typeText = TextType.MEDIUM,
                textBody = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TextComposable(
                typeText = TextType.SMALL,
                textBody = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            TextComposable(
                typeText = TextType.EXTRA_SMALL,
                textBody = stringResource(R.string.lorem_ipsum),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}
package com.example.tophair.app.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tophair.R
import com.example.tophair.app.data.entities.enum.TextType
import com.example.tophair.app.utils.fonts.TextComposable

@Composable
fun RegisterComponent(
    componentContent: @Composable ColumnScope.() -> Unit
) {
    CustomSystemBar()

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val constraints = with(LocalDensity.current) { constraints }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color(4, 23, 32)
                )
        ) {
            Image(
                painter = painterResource(id = R.mipmap.background_tela_inicial),
                contentDescription = "Background tela inicial",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(28.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(modifier = Modifier) {
                    Image(
                        painter = painterResource(
                            id = R.mipmap.logo_inicial
                        ),
                        contentDescription = "TopHair Logo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                    )
                }

                MarginSpace(12.dp)

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    content = componentContent
                )

                Spacer(modifier = Modifier.weight(1f))

                Box(modifier = Modifier.fillMaxWidth()) {
                    TextComposable(
                        typeText = TextType.EXTRA_SMALL,
                        textBody = stringResource(R.string.txt_politicas_e_termos),
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
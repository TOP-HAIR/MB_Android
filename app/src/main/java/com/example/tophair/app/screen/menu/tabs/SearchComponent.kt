package com.example.tophair.app.screen.menu.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.example.tophair.R
import com.example.tophair.app.utils.CustomLogo
import com.example.tophair.app.utils.MarginSpace

@Preview(showBackground = true)
@Composable
fun SearchComponent() {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            CustomLogo()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){

                Image(
                    painter = painterResource(id = R.mipmap.search_icon),
                    contentDescription = "Icon Calendar",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )

                MarginSpace(24.dp)

                Text(
                    stringResource(R.string.titulo_tela_de_busca_sem_resultado),
                    style = TextStyle(color = Color.Black,textAlign = TextAlign.Center),
                    fontSize = 28.sp,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                )

                MarginSpace(12.dp)

                Text(
                    stringResource(R.string.txt_tela_de_busca_sem_resultado),
                    style = TextStyle(color = Color.Black,textAlign = TextAlign.Center),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                )

            }

            MarginSpace(40.dp)
        }
}
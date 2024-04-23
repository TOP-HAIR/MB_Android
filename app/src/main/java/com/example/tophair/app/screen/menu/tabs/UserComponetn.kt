package com.example.tophair.app.screen.menu.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.example.tophair.R
import com.example.tophair.app.utils.MarginSpace

@Preview(showBackground = true)
@Composable
fun UserComponent() {

    val contextoPerfil = LocalContext.current
    val temaTopHair = Color(red = 4, green = 23, blue = 32)
    val textoSobreposto = Color(red = 0, green = 0, blue = 0, alpha = 200)

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(color = Color.LightGray, shape = RectangleShape),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(bottom = 20.dp)
                .background(color = temaTopHair, shape = RectangleShape)
                .align(alignment = Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.mipmap.logo_mobile_svg),
                contentDescription = "Logo Top Hair",
                modifier = Modifier
                    .height(200.dp)
                    .width(160.dp)
            )

        }

        MarginSpace(height = 15.dp)

        Image(painter = painterResource(id = R.mipmap.imagem_perfil),
            contentDescription = "Ícone de imagem de perfil",
            modifier = Modifier
                .height(150.dp)
                .width(200.dp)
        )

        MarginSpace(height = 35.dp)

        Text(text = stringResource(id = R.string.titulo_tela_de_perfil),
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)

        MarginSpace(height = 20.dp)

       // TextField()

        MarginSpace(height = 20.dp)

       // TextField()

        MarginSpace(height = 20.dp)

      // TextField()

        MarginSpace(height = 50.dp)

     // TextField()

        MarginSpace(height = 25.dp)

     //TextField()
        
    }

}


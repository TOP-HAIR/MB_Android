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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.example.tophair.R

@Preview(showBackground = true)
@Composable
fun CalendarComponent() {
    Text("Calendar", color = Color.Red)

    val contextoAgendamento = LocalContext.current
    val temaTopHair = Color(red = 4, green = 23, blue = 32)
    val textoSobreposto = Color(red = 0, green = 0, blue = 0, alpha = 200)

    Column(
        modifier = Modifier.fillMaxSize(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
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

     //   if () {

    //    }

        //   else () {

    //    }

    //    when()  {}

    }
}
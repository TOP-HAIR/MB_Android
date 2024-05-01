package com.example.tophair.app.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tophair.R

@Composable
fun CustomButton(text: String, onClick: () -> Unit?, color: Color = Color(0xFF26A69A)) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = color),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text, fontSize = 18.sp)
    }
}


@Composable
fun CustomRowWithDividers(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.txt_condicional_inicial),
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(color = Color.White)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text, color = Color.White)
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(color = Color.White)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CustomLogo() {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val temaTopHair = Color(red = 4, green = 23, blue = 32)
    Column(modifier = Modifier
        .height(110.dp)) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = temaTopHair, shape = RectangleShape),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.mipmap.logo_mobile),
                contentDescription = "Logo Top Hair",
                modifier = Modifier
                    .heightIn(max = screenHeight * 10 / 100)
                    .width(240.dp)
            )
        }
    }

}
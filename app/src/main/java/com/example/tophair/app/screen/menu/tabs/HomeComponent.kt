package com.example.tophair.app.screen.menu.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tophair.R
import com.example.tophair.app.utils.MarginSpace

@Preview(showBackground = true)
@Composable
fun HomeComponent() {

    val contextoHome = LocalContext.current
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

            // AsyncImage(model = R.mipmap.frame_25,
            //    contentDescription = "Logo da Top Hair")

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .align(alignment = Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.mipmap.icon_tintura_para_cabelo),
                contentDescription = "Serviço de pintura de cabelo",
                modifier = Modifier
                    .padding(start = 25.dp, end = 20.dp)
                    .width(85.dp)
                    .height(90.dp)
                    .border(2.dp, color = Color.DarkGray, RoundedCornerShape(10.dp))
                    .align(alignment = Alignment.CenterVertically),

                )

            Image(
                painter = painterResource(id = R.mipmap.icon_cabelo_masculino_curto),
                contentDescription = "Cabelo Masculino",
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .width(85.dp)
                    .height(90.dp)
                    .border(2.dp, color = Color.DarkGray, shape = RoundedCornerShape(10.dp))
                    .align(alignment = Alignment.CenterVertically)
            )


            Image(
                painter = painterResource(id = R.mipmap.icon_homem_com_barba),
                contentDescription = "Barba de Homem",
                modifier = Modifier
                    .padding(start = 20.dp, end = 25.dp)
                    .width(85.dp)
                    .height(90.dp)
                    .border(2.dp, color = Color.DarkGray, shape = RoundedCornerShape(10.dp))
                    .align(alignment = Alignment.CenterVertically)
            )

        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
//
            Text(
                text = stringResource(id = R.string.txt_tintura),
                modifier = Modifier.padding(start = 43.dp)
            )

            Spacer(modifier = Modifier.width(73.dp))

            Text(text = stringResource(id = R.string.txt_cabelo_masculino))

            Spacer(modifier = Modifier.width(77.dp))

            Text(text = stringResource(id = R.string.txt_barba))

        }

        MarginSpace(height = 30.dp)

        Text(
            text = stringResource(id = R.string.txt_estabelecimentos_mais_avaliados),
            fontSize = 18.sp
        )

        MarginSpace(height = 15.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) {

            Image(
                painter = painterResource(id = R.mipmap.imagem_de_fundo),
                contentDescription = "Imagem de Fundo 01",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )

            Row(
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .padding(top = 13.dp, end = 56.dp)
                    .width(90.dp)
                    .height(50.dp)
                    .background(color = textoSobreposto, shape = RoundedCornerShape(5.dp))
            ) {

                Text(
                    text = stringResource(id = R.string.txt_taxa_de_avaliacao),
                    modifier = Modifier.padding(start = 15.dp, top = 5.dp),
                    fontSize = 24.sp,
                    color = Color.White
                )

                Image(
                    painter = painterResource(id = R.mipmap.imagem_estrela_dourada),
                    contentDescription = "Imagem de estrela dourada",
                    modifier = Modifier
                        .width(20.dp)
                        .height(10.dp)
                        .align(Alignment.CenterVertically),
                    alignment = Alignment.CenterEnd

                )

                Text(
                    text = stringResource(id = R.string.txt_qtd_de_avaliacoes),
                    color = Color.White,
                    fontSize = 5.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 20.dp, end = 10.dp)
                )


            }

            Row(
                modifier = Modifier
                    .align(alignment = Alignment.BottomStart)
                    .padding(start = 56.dp, bottom = 18.dp, end = 56.dp)
                    .width(280.dp)
                    .height(60.dp)
                    .background(color = textoSobreposto, shape = RoundedCornerShape(2.dp))
            ) {

                //  Text(text = stringResource(id =),
                //    color = Color.White,
                //    fontSize = 5.sp,
                //    textAlign = TextAlign.Center,
                //    modifier = Modifier.padding(top = 20.dp, end = 10.dp)
                //  )


            }


        }

        MarginSpace(height = 10.dp)

        Text(
            text = stringResource(id = R.string.txt_estabelecimentos_recomendados),
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(start = 5.dp),
            fontSize = 18.sp
        )

        MarginSpace(height = 15.dp)


    }

    LazyColumn(
        modifier = Modifier.height(770.dp),
        state = rememberLazyListState(0, 0),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        userScrollEnabled = true,
    ) {
        items(1) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {

                Image(
                    painter = painterResource(id = R.mipmap.imagem_de_fundo),
                    contentDescription = "Imagem de Fundo 01",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )

                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.TopEnd)
                        .padding(top = 13.dp, end = 56.dp)
                        .width(90.dp)
                        .height(50.dp)
                        .background(color = textoSobreposto, shape = RoundedCornerShape(5.dp))
                ) {

                    Text(
                        text = stringResource(id = R.string.txt_taxa_de_avaliacao),
                        modifier = Modifier.padding(start = 15.dp, top = 5.dp),
                        fontSize = 24.sp,
                        color = Color.White
                    )

                    Image(
                        painter = painterResource(id = R.mipmap.imagem_estrela_dourada),
                        contentDescription = "Imagem de estrela dourada",
                        modifier = Modifier
                            .width(20.dp)
                            .height(10.dp)
                            .align(Alignment.CenterVertically),
                        alignment = Alignment.CenterEnd

                    )

                    Text(
                        text = stringResource(id = R.string.txt_qtd_de_avaliacoes),
                        color = Color.White,
                        fontSize = 5.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 20.dp, end = 10.dp)
                    )


                }

                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomStart)
                        .padding(start = 56.dp, bottom = 18.dp, end = 56.dp)
                        .width(280.dp)
                        .height(60.dp)
                        .background(color = textoSobreposto, shape = RoundedCornerShape(2.dp))
                ) {

                    //  Text(text = stringResource(id =),
                    //    color = Color.White,
                    //    fontSize = 5.sp,
                    //    textAlign = TextAlign.Center,
                    //    modifier = Modifier.padding(top = 20.dp, end = 10.dp)
                    //  )


                }


            }

        }
    }

}





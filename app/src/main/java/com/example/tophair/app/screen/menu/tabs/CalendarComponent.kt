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
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.example.tophair.R
import com.example.tophair.app.data.viewmodel.AgendaViewModel
import com.example.tophair.app.data.viewmodel.EmpresaViewModel
import com.example.tophair.app.utils.CustomLogo
import com.example.tophair.app.utils.HideSystemBars
import com.example.tophair.app.utils.MarginSpace
import java.time.LocalDateTime

@Composable
fun CalendarComponent(agendaViewModel: AgendaViewModel, empresaViewModel: EmpresaViewModel) {
    val agendaState = agendaViewModel.agenda.observeAsState()
    val agenda = agendaState.value ?: emptyList()

    HideSystemBars()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        CustomLogo()

        if (agenda != null && agenda.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(34.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.txt_agendamentos),
                    color = Color.Black,
                    fontSize = 18.sp
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    agenda.forEach { agenda ->
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            val dataResponse: LocalDateTime? = agenda?.start

                            Text(
                                text = dataResponse.toString() ?: "Data inválida",
                                color = Color.Black,
                                fontSize = 20.sp
                            )

                            Text(
                                text = agenda?.end.toString(),
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        }

                        Image(
                            modifier = Modifier
                                .fillMaxWidth(),
                            painter = painterResource(id = R.mipmap.no_image),
                            contentDescription = agenda.empresaDto?.razaoSocial,
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 4.dp),
                            text = agenda?.empresaDto?.razaoSocial.toString(),
                            color = Color.Black,
                            fontSize = 18.sp
                        )

                        Text(
                            text = agenda?.title.toString(),
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.calendar_icon),
                    contentDescription = "Icon Calendar",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )

                MarginSpace(24.dp)

                Text(
                    stringResource(R.string.titulo_tela_de_agendamento_sem_reservas),
                    style = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
                    fontSize = 28.sp,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                )

                MarginSpace(12.dp)

                Text(
                    stringResource(R.string.txt_tela_de_agendamento_sem_reservas),
                    style = TextStyle(color = Color.Black, textAlign = TextAlign.Center),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                )
            }
        }

        MarginSpace(40.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarComponentPreview() {
    val fakeAgendaViewModel = AgendaViewModel()
    val fakeEmpresaViewModel = EmpresaViewModel()

    CalendarComponent(
        agendaViewModel = fakeAgendaViewModel,
        empresaViewModel = fakeEmpresaViewModel
    )
}
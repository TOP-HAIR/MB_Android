package com.example.tophair.app.screen.menu.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.example.tophair.R
import com.example.tophair.app.data.entities.enum.TextType
import com.example.tophair.app.data.entities.enum.TitleType
import com.example.tophair.app.data.viewmodel.AgendaViewModel
import com.example.tophair.app.data.viewmodel.EmpresaViewModel
import com.example.tophair.app.utils.CircleLoader
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.app.utils.fonts.TextComposable
import com.example.tophair.app.utils.fonts.TitleComposable
import java.time.LocalDateTime

@Composable
fun CalendarComponent(agendaViewModel: AgendaViewModel, empresaViewModel: EmpresaViewModel) {
    val agendaState = agendaViewModel.agenda.observeAsState()
    val agenda = agendaState.value ?: emptyList()
    val agendaLoader by agendaViewModel.agendaLoader.observeAsState(true)

    agendaViewModel.getAgendaUser()

    if (agendaLoader) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircleLoader(
                color = Color(0xFF2F9C7F),
                secondColor = Color(0xFF0F3D3A),
                modifier = Modifier.size(100.dp),
                isVisible = agendaLoader
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
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
                            Box(modifier = Modifier.clip(RoundedCornerShape(12.dp))) {
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
                                    text = agenda?.status.toString(),
                                    color = Color.Black,
                                    fontSize = 14.sp
                                )
                            }
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

                    TitleComposable(
                        typeTitle = TitleType.H2,
                        textTitle = stringResource(R.string.titulo_tela_de_agendamento_sem_reservas),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        textColor = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                    )

                    MarginSpace(12.dp)

                    TextComposable(
                        typeTitle = TextType.MEDIUM,
                        textTitle = stringResource(R.string.txt_tela_de_agendamento_sem_reservas),
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        textColor = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                    )
                }
            }

            MarginSpace(40.dp)
        }
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
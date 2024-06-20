package com.example.tophair.app.screen.menu.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.app.utils.fonts.TextComposable
import com.example.tophair.app.utils.fonts.TitleComposable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CalendarComponent(agendaViewModel: AgendaViewModel, empresaViewModel: EmpresaViewModel) {
    val agendaState = agendaViewModel.agenda.observeAsState()
    val agendaCan = agendaViewModel.agendaCan.observeAsState()
    val agenda = agendaState.value ?: emptyList()
    val agendaLoader by agendaViewModel.agendaLoader.observeAsState(true)

    LaunchedEffect(Unit) {
        agendaViewModel.getAgendaUser()
    }


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
                .padding(horizontal = 20.dp)
        ) {
            if (agenda != null && agenda.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    MarginSpace(height = 12.dp)

                    TitleComposable(
                        typeTitle = TitleType.H2,
                        textTitle = stringResource(R.string.txt_agendamentos),
                        fontWeight = FontWeight.Normal
                    )

                    MarginSpace(height = 12.dp)

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        agenda.forEach { agenda ->
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp, vertical = 18.dp)
                            ) {
                                Column() {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = formatarDataHoraParaPadraoBrasileiro(agenda?.start) ?: "Data inválida",
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                            modifier = Modifier
                                        )

                                        Spacer(modifier = Modifier.width(8.dp))

                                        Text(
                                            text = formatarDataHoraParaPadraoBrasileiro(agenda?.end) ?: "Data inválida",
                                            color = Color.Black,
                                            fontSize = 12.sp,
                                            modifier = Modifier.weight(1f)
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.width(140.dp)) {
                                            Text(
                                                modifier = Modifier
                                                    .padding(vertical = 4.dp),
                                                text = agenda?.empresaDto?.razaoSocial.toString(),
                                                color = Color.Black,
                                                fontSize = 16.sp
                                            )

                                            Text(
                                                text = agenda?.status.toString(),
                                                color = Color.Black,
                                                fontSize = 14.sp
                                            )
                                        }

                                        CustomButton(
                                            stringResource(R.string.btn_txt_cancelar),
                                            modifier = Modifier,
                                            color = Color(0xFFFF0000),
                                            onClick = {
                                                agenda?.idAgenda?.let {
                                                    agendaViewModel.putCancelarAgenda(it)
                                                }
                                            },
                                            typeText = TextType.SMALL,
                                        )
                                    }
                                }
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
                            .width(80.dp)
                            .height(80.dp)
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
                        typeText = TextType.MEDIUM,
                        textBody = stringResource(R.string.txt_tela_de_agendamento_sem_reservas),
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

fun formatarDataHoraParaPadraoBrasileiro(dataHora: String? = ""): String {
    val formatoEntrada = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

    val dataHoraOriginal: Date = formatoEntrada.parse(dataHora)

    val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale("pt", "BR"))

    return formatoBrasileiro.format(dataHoraOriginal)
}
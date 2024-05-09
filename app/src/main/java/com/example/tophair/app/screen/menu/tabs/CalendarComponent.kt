package com.example.tophair.app.screen.menu.tabs

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import com.example.tophair.R
import com.example.tophair.app.data.entities.Agenda
import com.example.tophair.app.data.viewmodel.AgendaViewModel
import com.example.tophair.app.data.viewmodel.EmpresaViewModel
import com.example.tophair.app.data.viewmodel.UserViewModel
import com.example.tophair.app.screen.menu.MenuNavigationView
import com.example.tophair.app.utils.CustomLogo
import com.example.tophair.app.utils.HideSystemBars
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.ui.theme.TopHairTheme
import java.util.Locale


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

//        when (agenda) {
//            is List<AgendaUser> -> {
//                // Se a lista de agendas não estiver vazia, exiba a coluna com as reservas
//                // Conteúdo da coluna com as reservas
//            }
//            else -> {
//                // Se a lista de agendas estiver vazia ou for nula, exiba a coluna sem reservas
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(20.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    // Conteúdo da coluna sem reservas
//                }
//            }
//        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp))
        {
            if (agenda != null && agenda.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ){
                    Column(modifier = Modifier
                        .fillMaxSize()
                    ) {
                        androidx.compose.material3.Text(
                            modifier = Modifier
                                .padding( vertical = 12.dp),
                            text = stringResource(id = R.string.txt_agendamentos),
                            color = Color.Black,
                            fontSize = 18.sp
                        )

                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White)
                        )
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding( vertical = 12.dp)
                        ) {
                            agenda.forEach { agenda ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding( vertical = 4.dp)
                                ) {
                                    val dataResponse = agenda?.data
                                    val dataFormata = java.text.SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                        .parse(dataResponse)?.let { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(it) }

                                    Text(
                                        text = dataFormata.toString() ?: "Data inválida",
                                        color = Color.Black,
                                        fontSize = 20.sp
                                    )
                                    Text(
                                        text = agenda?.hora.toString(),
                                        color = Color.Black,
                                        fontSize = 14.sp
                                    )
                                }

                                Image(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    painter = painterResource(id = R.mipmap.no_image) ,
                                    contentDescription = agenda.empresaDto?.razaoSocial,
                                    contentScale = ContentScale.Crop
                                )

                                Text(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding( vertical = 4.dp),
                                    text = agenda?.empresaDto?.razaoSocial.toString(),
                                    color = Color.Black,
                                    fontSize = 18.sp)

                                Text(text = agenda?.status.toString(),
                                    color = Color.Black,
                                    fontSize = 14.sp)
                                }
                            }
    //                    }
                    }
                }
            } else {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){

                    Image(
                        painter = painterResource(id = R.mipmap.calendar_icon),
                        contentDescription = "Icon Calendar",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                    )

                    MarginSpace(24.dp)

                    Text(stringResource(R.string.titulo_tela_de_agendamento_sem_reservas),
                        style = TextStyle(color = Color.Black,textAlign = TextAlign.Center),
                        fontSize = 28.sp,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                    )

                    MarginSpace(12.dp)

                    Text(stringResource(R.string.txt_tela_de_agendamento_sem_reservas),
                        style = TextStyle(color = Color.Black,textAlign = TextAlign.Center),
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                    )

                }
            }
        }


        MarginSpace(40.dp)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CalendarComponentPreview() {
//
//    val fakeAgendaViewModel = AgendaViewModel()
//  val fakeEmpresaViewModel = EmpresaViewModel()
//
//
//    CalendarComponent(agendaViewModel = fakeAgendaViewModel, empresaViewModel = fakeEmpresaViewModel )
//}
package com.example.tophair.app.screen.menu.tabs

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tophair.R
import com.example.tophair.app.data.entities.AgendaPost
import com.example.tophair.app.data.entities.UserLogin
import com.example.tophair.app.data.entities.enum.NavMenuEnum
import com.example.tophair.app.data.entities.enum.StatusAgendamento
import com.example.tophair.app.data.entities.enum.TextType
import com.example.tophair.app.data.entities.enum.TitleType
import com.example.tophair.app.data.entities.enum.TitleType.*
import com.example.tophair.app.data.viewmodel.AgendaViewModel
import com.example.tophair.app.data.viewmodel.ServicoViewModel
import com.example.tophair.app.data.viewmodel.UserViewModel
import com.example.tophair.app.utils.CircleLoader
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.app.utils.fonts.TextComposable
import com.example.tophair.app.utils.fonts.TitleComposable
import com.example.tophair.ui.theme.TopHairTheme
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.time.Duration
import java.time.ZonedDateTime


private var hour: Int = 0
private var minute: Int = 0

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaComponent(
    navController: NavHostController,
    servicoViewModel: ServicoViewModel,
    agendaViewModel: AgendaViewModel,
    userViewModel: UserViewModel,
    idServico: Int,
    idEmpresa: Int,
) {
    val vincularUsuario = agendaViewModel.vincularUsuario.observeAsState()
    val vincularServico = agendaViewModel.vincularServico.observeAsState()
    val vincularEmpresa = agendaViewModel.vincularEmpresa.observeAsState()


    val servicoState = servicoViewModel.servicoEmpresa.observeAsState()
    val user = userViewModel.user.observeAsState()
    val agendaState = agendaViewModel.agendaRes.observeAsState()
    val servicoLoader by servicoViewModel.servicoLoader.observeAsState(true)
    var showTimePicker by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val focusManager = LocalFocusManager.current
    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()
    var selectedDate by remember {
        mutableStateOf("")
    }
    var timeText by remember { mutableStateOf("") }

    LaunchedEffect(idServico) {
        servicoViewModel.getServicoEmpresa(idServico)
    }

    if (servicoLoader || servicoState == null) {
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
                isVisible = servicoLoader
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showDatePickerDialog) {
                DatePickerDialog(
                    onDismissRequest = { showDatePickerDialog = false },
                    confirmButton = {
                        Button(
                            onClick = {
                                datePickerState
                                    .selectedDateMillis?.let { millis ->
                                        selectedDate = millis.toBrazilianDateFormat()
                                    }
                                showDatePickerDialog = false
                            }) {
                            Text(text = stringResource(id = R.string.txt_data_escolher))
                        }
                    }) {
                    DatePicker(state = datePickerState)
                }
            }

            TitleComposable(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                typeTitle = H1,
                textTitle = stringResource(id = R.string.titulo_agendamento),
                fontWeight = FontWeight.Normal,
                textColor = Color.Black,
                textAlign = TextAlign.Center
            )

            MarginSpace(height = 8.dp)

            TextComposable(
                typeText = TextType.MEDIUM,
                textBody = stringResource(R.string.txt_agendamento),
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                textColor = Color.Black
            )

            MarginSpace(height = 16.dp)

            TextComposable(
                typeText = TextType.LARGE,
                textBody = "${servicoState.value?.nomeServico}: R$ ${servicoState.value?.preco}",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                textColor = Color.Black
            )

            MarginSpace(height = 8.dp)

            OutlinedTextField(
                value = selectedDate,
                onValueChange = { selectedDate = it },
                label = {
                    Text(
                        stringResource(R.string.txt_data_agenda),
                        style = TextStyle(color = Color.Black)
                    )
                },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .onFocusEvent {
                        if (it.isFocused) {
                            showDatePickerDialog = true
                            focusManager.clearFocus(force = true)
                        }
                    },
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
                readOnly = true,
                singleLine = true
            )

            MarginSpace(8.dp)

            OutlinedTextField(
                value = timeText,
                onValueChange = { timeText = it },
                label = {
                    Text(
                        stringResource(R.string.txt_horario_input),
                        style = TextStyle(color = Color.Black)
                    )
                },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .onFocusEvent {
                        if (it.isFocused) {
                            popTimePicker(context) { selectedHour, selectedMinute ->
                                hour = selectedHour
                                minute = selectedMinute
                                timeText =
                                    String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
                            }
                        }
                    },
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
                readOnly = true,
                singleLine = true
            )

            MarginSpace(8.dp)

            CustomButton(stringResource(R.string.btn_fazer_agendamento), onClick = {
                if (!selectedDate.isNullOrEmpty() && !timeText.isNullOrEmpty()) {
                    var datetimeFormatado = formatDateTime(date = selectedDate, time = timeText)
                    var dateTimeFinal = addTimeToFormattedDateTime(
                        formattedDateTime = datetimeFormatado.toString(),
                        durationToAdd = servicoState?.value?.qtdTempoServico
                    )

                    val obj = AgendaPost(
                        startTime = datetimeFormatado.toString(),
                        endTime = dateTimeFinal.toString(),
                        background = StatusAgendamento.AGENDADO.toString(),
                        title = "${(servicoState?.value?.nomeServico).toString()} - ${(user?.value?.nomeCompleto).toString()}"
                    )

                    agendaViewModel.postAgenda(obj)
                }
            })
        }

        LaunchedEffect(agendaState.value) {
            agendaState.value?.let { agendaResponse ->
                Log.d("Agendamento", "LaunchedEffect disparado ${agendaResponse.idAgenda}")


                agendaResponse.idAgenda?.let { idAgenda ->
                    agendaViewModel.putAgendaVincularUsuario(idAgenda.toInt())
                    agendaViewModel.putAgendaVincularEmpresa(idAgenda.toInt(), idEmpresa)
                    agendaViewModel.putAgendaVincularServico(idAgenda.toInt(), idServico)
                }
            }

            if (vincularUsuario != null && vincularServico != null && vincularEmpresa != null) {
                navController.navigate(NavMenuEnum.CALENDAR.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AgendaComponentPreview() {
    val fakeServicoViewModel = ServicoViewModel()
    val fakeAgendaViewModel = AgendaViewModel()
    val fakeUserViewModel = UserViewModel()

    TopHairTheme {
        AgendaComponent(
            rememberNavController(),
            fakeServicoViewModel,
            fakeAgendaViewModel,
            fakeUserViewModel,
            6,
            8,
        )
    }
}

private fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}

private fun popTimePicker(context: Context, onTimeSet: (Int, Int) -> Unit) {
    val onTimeSetListener = TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
        hour = selectedHour
        minute = selectedMinute
        onTimeSet(hour, minute)
    }

    val timePickerDialog = TimePickerDialog(context, onTimeSetListener, hour, minute, true)
    timePickerDialog.setTitle("Select Time")
    timePickerDialog.show()
}

private fun formatDateTime(date: String, time: String): String {
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val localDate = LocalDate.parse(date, dateFormatter)
    val localTime = LocalTime.parse(time, timeFormatter)

    val localDateTime = LocalDateTime.of(localDate, localTime)

    val zonedDateTime = localDateTime.atZone(ZoneOffset.UTC)

    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    return zonedDateTime.format(outputFormatter)
}

fun addTimeToFormattedDateTime(formattedDateTime: String?, durationToAdd: String?): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")

    val zonedDateTime = ZonedDateTime.parse(formattedDateTime, inputFormatter)

    val parts = durationToAdd!!.split(":")
    val duration = Duration.ofHours(parts[0].toLong())
        .plusMinutes(parts[1].toLong())
        .plusSeconds(parts[2].toLong())

    val newZonedDateTime = zonedDateTime.plus(duration)

    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    return newZonedDateTime.format(outputFormatter)
}
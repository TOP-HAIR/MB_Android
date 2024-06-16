package com.example.tophair.app.screen.menu.tabs

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.tophair.R
import com.example.tophair.app.data.entities.enum.TextType
import com.example.tophair.app.data.entities.enum.TitleType
import com.example.tophair.app.data.viewmodel.EmpresaViewModel
import com.example.tophair.app.data.viewmodel.ServicoViewModel
import com.example.tophair.app.utils.CardComponent
import com.example.tophair.app.utils.CircleLoader
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.fonts.TextComposable
import com.example.tophair.app.utils.fonts.TitleComposable
import com.example.tophair.ui.theme.TopHairTheme

@Composable
fun EmpresaComponent(
    navController: NavHostController,
    idEmpresa: Int,
    servicoViewModel: ServicoViewModel,
    empresaViewModel: EmpresaViewModel
) {
    val empresaState = empresaViewModel.empresaGetId.observeAsState()
    val servicoState = servicoViewModel.empresaServicoList.observeAsState()
    val servico = servicoState.value ?: emptyList()
//    val openDialog = remember { mutableStateOf(false) }
//    var servicoSelecionado = remember { mutableStateOf("") }
//    var dataAgendamento = remember { mutableStateOf("") }
//    var horarioAgendamento = remember { mutableStateOf("") }
    val empresaLoader by empresaViewModel.empresaLoader.observeAsState(true)
    val servicoLoader by servicoViewModel.servicoLoader.observeAsState(true)

    empresaViewModel.getEmpresaById(idEmpresa)
    servicoViewModel.getServicoEmpresa(idEmpresa)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if (empresaState != null) {
            val empresa = empresaState.value

            if (empresaLoader && servicoLoader) {
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
                        isVisible = empresaLoader && servicoLoader
                    )
                }
            } else {
                if (!empresa?.arquivos.isNullOrEmpty()) {
                    AsyncImage(
                        model = "http://34.237.189.174/api/arquivos/exibir/${
                            empresa?.arquivos?.get(
                                0
                            )?.id
                        }",
                        contentDescription = empresa?.razaoSocial,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .height(250.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.mipmap.no_image),
                        contentDescription = "Sem Imagem",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .height(200.dp)
                    )
                }

                TitleComposable(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    typeTitle = TitleType.H1,
                    textTitle = empresa?.razaoSocial.toString(),
                    fontWeight = FontWeight.SemiBold,
                    textColor = Color.Black
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .background(color = Color.White)
                )

                TitleComposable(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    typeTitle = TitleType.H2,
                    textTitle = stringResource(id = R.string.txt_servicos_empresa),
                    fontWeight = FontWeight.Medium,
                    textColor = Color.Black
                )

                if (servico != null && servico.isNotEmpty()) {
                    servico.forEach { servicoItem ->
                        CardComponent(
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 4.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    TitleComposable(
                                        typeTitle = TitleType.H3,
                                        textTitle = servicoItem.nomeServico.toString(),
                                        fontWeight = FontWeight.Normal,
                                        textColor = Color.Black
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Row(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .width(120.dp)
                                                .align(Alignment.Top)
                                        ) {
                                            TextComposable(
                                                typeText = TextType.LARGE,
                                                textBody = "R$ ${servicoItem.preco.toString()}",
                                                fontWeight = FontWeight.Light,
                                                textColor = Color.Black
                                            )
                                        }

                                        Box(
                                            modifier = Modifier
                                                .width(120.dp)
                                                .align(Alignment.Bottom)
                                        ) {
                                            CustomButton(
                                                stringResource(R.string.btn_txt_agendar),
                                                onClick = {
                                                    navController.navigate("Agenda/${idEmpresa}/${servicoItem.idServico}")
                                                },
                                                typeText = TextType.SMALL,
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

//        if (openDialog.value) {
//            AlertDialog(
//                containerColor = Color.White,
//                onDismissRequest = { openDialog.value = false },
//                title = {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//
//                        ) {
//
//                        Text(
//                            text = stringResource(id = R.string.txt_servico_selecionado),
//                            textAlign = TextAlign.Center,
//                            fontSize = 16.sp,
//                            color = Color.Black,
//                        )
//
//                        Text(
//                            text = servicoSelecionado.value.toString(),
//                            textAlign = TextAlign.Center,
//                            fontWeight = FontWeight.ExtraBold,
//                            fontSize = 14.sp,
//                            color = Color(0x1A5646),
//                        )
//                    }
//                },
//                text = {
//                    Column {
//                        TextField(
//                            value = dataAgendamento.value,
//                            onValueChange = { dataAgendamento.value = it },
//                            label = { Text(stringResource(id = R.string.txt_data_input)) }
//                        )
//
//                        Spacer(modifier = Modifier.height(16.dp))
//
//                        TextField(
//                            value = horarioAgendamento.value,
//                            onValueChange = { horarioAgendamento.value = it },
//                            label = { Text(stringResource(id = R.string.txt_horario_input)) }
//                        )
//                    }
//                },
//                dismissButton = {
//                    TextButton(
//                        onClick = {
//                            openDialog.value = false
//                        }
//                    ) {
//                        Text(
//                            stringResource(id = R.string.btn_txt_servico_dismiss),
//                            fontSize = 14.sp,
//                            color = Color.Black,
//                        )
//                    }
//                },
//                confirmButton = {
//                    Button(
//                        onClick = {
//                            openDialog.value = false
//                        }
//                    ) {
//                        Text(
//                            stringResource(id = R.string.btn_txt_servico_confirm),
//                            fontSize = 14.sp,
//                            color = Color.Black,
//                        )
//                    }
//                }
//            )
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmpresaComponentPreview() {
    val fakeEmpresaViewModel = EmpresaViewModel()
    val fakeServicoViewModel = ServicoViewModel()

    TopHairTheme {
        EmpresaComponent(
            rememberNavController(),
            6,
            fakeServicoViewModel,
            fakeEmpresaViewModel,
        )
    }
}


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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    val empresaLoader by empresaViewModel.empresaLoader.observeAsState(true)
    val servicoLoader by servicoViewModel.servicoLoader.observeAsState(true)

    LaunchedEffect(Unit) {
        empresaViewModel.getEmpresaById(idEmpresa)
        servicoViewModel.getListaServicoEmpresa(idEmpresa)
    }

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
                        model = "https://tophair.zapto.org/api/arquivos/exibir/${
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
                                .padding(horizontal = 20.dp, vertical = 12.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    TitleComposable(
                                        typeTitle = TitleType.H3,
                                        textTitle = servicoItem.nomeServico.toString(),
                                        fontWeight = FontWeight.SemiBold,
                                        textColor = Color.Black
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .width(140.dp)
                                        ) {
                                            TextComposable(
                                                typeText = TextType.ULTRA_EXTRA_LARGE,
                                                textBody = "R$ ${servicoItem.preco.toString()}",
                                                fontWeight = FontWeight.Light,
                                                textColor = Color.Black
                                            )
                                        }

                                        CustomButton(
                                            stringResource(R.string.btn_txt_agendar),
                                            modifier = Modifier,
                                            onClick = {
                                                if (servicoItem.idServico != null) {
                                                    servicoViewModel.getListaServicoEmpresa(
                                                        servicoItem.idServico
                                                    )

                                                    navController.navigate("Agenda/${idEmpresa}/${servicoItem.idServico}")
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
            }
        }
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


package com.example.tophair.app.screen.menu.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import com.example.tophair.R
import com.example.tophair.app.data.entities.enum.TextType
import com.example.tophair.app.data.entities.enum.TitleType
import com.example.tophair.app.data.viewmodel.EmpresaViewModel
import com.example.tophair.app.utils.CircleLoader
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.app.utils.fonts.TextComposable
import com.example.tophair.app.utils.fonts.TitleComposable
import com.example.tophair.ui.theme.TopHairTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(empresaViewModel: EmpresaViewModel, navController: NavHostController) {

    val empresaHomeState = empresaViewModel.empresaHome.observeAsState()!!
    val empresaFiltroState = empresaViewModel.empresaFiltro.observeAsState()!!
    val empresaFiltro = empresaFiltroState.value ?: emptyList()
    val empresaHome = empresaHomeState.value ?: emptyList()
    val empresaLoader by empresaViewModel.empresaLoader.observeAsState(true)

    var estado by remember { mutableStateOf("") }
    var servico by remember { mutableStateOf("") }
    var nomeEmpresa by remember { mutableStateOf("") }

    if (empresaHome.isNullOrEmpty()) {
        empresaViewModel.getHomeEmpresas()
    }

    if (empresaLoader) {
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
                isVisible = empresaLoader
            )
        }
    } else {

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        if ((empresaHome != null && empresaHome.isNotEmpty()) && empresaFiltro.isNullOrEmpty()) {
            empresaHome.forEach { empresa ->
                Box(
                    modifier = Modifier
                        .padding(34.dp)
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            navController.navigate("Empresa/${empresa.idEmpresa}")
                        }
                ) {
                    if (!empresa.arquivoDtos.isNullOrEmpty()) {
                        AsyncImage(
                            model = "http://34.237.189.174/api/arquivos/exibir/${
                                empresa.arquivoDtos?.get(
                                    0
                                )?.id
                            }",
                            contentDescription = empresa.razaoSocial,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.mipmap.no_image),
                            contentDescription = "Sem Imagem",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Column(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .background(Color(0x80000000))
                            .padding(18.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${"%d".format(empresa.mediaNivelAvaliacoes)}/5.0",
                                color = Color.White,
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "⭐",
                                fontSize = 8.sp,
                                color = Color.White
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .background(Color(0x80000000))
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = empresa.razaoSocial.toString(),
                                color = Color.White,
                                fontSize = 14.sp
                            )

                            Text(
                                text = "${empresa.endereco?.logradouro}, ${empresa.endereco?.numero}\n" +
                                        "${empresa.endereco?.cep} - ${empresa.endereco?.cidade}/${empresa.endereco?.estado}",
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

            }
        } else {
            if (empresaFiltro != null && empresaFiltro.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    OutlinedTextField(
                        value = nomeEmpresa,
                        onValueChange = { nomeEmpresa = it },
                        label = {
                            androidx.compose.material3.Text(
                                stringResource(R.string.txt_nome_empresa),
                                style = TextStyle(color = Color.Black)
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            cursorColor = Color.Black
                        ),
                        singleLine = true
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(vertical = 8.dp)
                    ) {
                        OutlinedTextField(
                            value = servico,
                            onValueChange = { servico = it },
                            label = {
                                androidx.compose.material3.Text(
                                    stringResource(R.string.txt_servico),
                                    style = TextStyle(color = Color.Black)
                                )
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            ),
                            modifier = Modifier
                                .weight(0.67f),
                            textStyle = TextStyle(color = Color.Black),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Black,
                                cursorColor = Color.Black
                            ),
                            singleLine = true
                        )

                        Spacer(
                            modifier = Modifier
                                .weight(0.03f)
                        )

                        OutlinedTextField(
                            value = estado,
                            onValueChange = { estado = it },
                            label = {
                                androidx.compose.material3.Text(
                                    stringResource(R.string.txt_estado),
                                    style = TextStyle(color = Color.Black)
                                )
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            ),
                            modifier = Modifier
                                .weight(0.30f),
                            textStyle = TextStyle(color = Color.Black),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Black,
                                cursorColor = Color.Black
                            ),
                            singleLine = true
                        )
                    }

                    CustomButton(stringResource(R.string.btn_txt_filtrar), onClick = {
                        if ((estado.isNotBlank() || servico.isNotBlank() || nomeEmpresa.isNotBlank())) {
                            val result =
                                empresaViewModel.getFiltroEmpresas(estado, servico, nomeEmpresa)
                        }
                    })

                    empresaFiltro.forEach { empresa ->
                        Box(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    navController.navigate("Empresa/${empresa.idEmpresa}")
                                }
                        ) {
                            if (!empresa.arquivoDtos.isNullOrEmpty()) {
                                AsyncImage(
                                    model = "http://34.235.119.206/api/arquivos/exibir/${
                                        empresa.arquivoDtos?.get(
                                            0
                                        )?.id
                                    }",
                                    contentDescription = empresa.razaoSocial,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Image(
                                    painter = painterResource(id = R.mipmap.no_image),
                                    contentDescription = "Sem Imagem",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .background(Color(0x80000000))
                                    .padding(18.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "${"%d".format(empresa.mediaNivelAvaliacoes)}/5.0",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(
                                        text = "⭐",
                                        fontSize = 8.sp,
                                        color = Color.White
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                                    .background(Color(0x80000000))
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(
                                        text = empresa.razaoSocial.toString(),
                                        color = Color.White,
                                        fontSize = 14.sp
                                    )

                                    Text(
                                        text = "${empresa.endereco?.logradouro}, ${empresa.endereco?.numero}\n" +
                                                "${empresa.endereco?.cep} - ${empresa.endereco?.cidade}/${empresa.endereco?.estado}",
                                        color = Color.White,
                                        fontSize = 12.sp
                                    )
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
                        painter = painterResource(id = R.mipmap.search_icon),
                        contentDescription = "Icon Calendar",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                    )

                    MarginSpace(24.dp)

                    TitleComposable(
                        typeTitle = TitleType.H2,
                        textTitle = stringResource(R.string.titulo_tela_de_busca_sem_resultado),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        textColor = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                    )

                    MarginSpace(12.dp)

                    TextComposable(
                        typeTitle = TextType.MEDIUM,
                        textTitle = stringResource(R.string.txt_tela_de_busca_sem_resultado),
                        fontWeight = FontWeight.Light,
                        textColor = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                    )
                }

                MarginSpace(40.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchComponentPreview() {
    val fakeEmpresaViewModel = EmpresaViewModel()

    TopHairTheme {
        SearchComponent(
            fakeEmpresaViewModel,
            rememberNavController()
        )
    }
}
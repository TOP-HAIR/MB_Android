package com.example.tophair.app.screen.menu.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.tophair.R
import com.example.tophair.app.data.entities.Empresa
import com.example.tophair.app.data.entities.enum.FilterServicoEnum
import com.example.tophair.app.data.viewmodel.EmpresaViewModel
import com.example.tophair.app.utils.CircleLoader
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.ui.theme.TopHairTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun HomeComponent(empresaViewModel: EmpresaViewModel, navController: NavHostController) {
    val empresasState = empresaViewModel.empresaTop5.observeAsState()!!
    val empresaHomeState = empresaViewModel.empresaHome.observeAsState()!!
    val empresasTop5 = empresasState.value ?: emptyList()
    val empresaHome = empresaHomeState.value ?: emptyList()
    val empresaLoader by empresaViewModel.empresaLoader.observeAsState(true)

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier
                    .padding(14.dp)
                    .fillMaxSize()
            ) {
                MarginSpace(height = 4.dp)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.Center
                ) {
                    FilterServicoEnum.values().forEach { icon ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Image(
                                painter = painterResource(id = icon.imagemFiltro),
                                contentDescription = icon.descricaoFiltro,
                                modifier = Modifier
                                    .size(85.dp, 90.dp)
                                    .border(
                                        2.dp,
                                        color = Color.DarkGray,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .clickable {
                                        navController.navigate("Buscar")
                                    }
                            )
                            Text(
                                stringResource(id = icon.textoFiltro),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                MarginSpace(height = 16.dp)

                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 4.dp),
                    text = stringResource(id = R.string.txt_estabelecimentos_mais_avaliados),
                    color = Color.Black,
                    fontSize = 16.sp
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .background(color = Color.White)
                )

                MarginSpace(height = 4.dp)

                if (empresasTop5 != null && empresasTop5.isNotEmpty()) {
                    EmpresaPager(empresas = empresasTop5, navController)
                }

                MarginSpace(height = 12.dp)

                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 4.dp),
                    color = Color.Black,
                    text = stringResource(id = R.string.txt_estabelecimentos_recomendados),
                    fontSize = 16.sp
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .background(color = Color.White)
                )

                MarginSpace(height = 4.dp)

                if (empresaHome != null && empresaHome.isNotEmpty()) {
                    empresaHome.forEach { empresa ->
                        Box(
                            modifier = Modifier
                                .padding(20.dp)
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
                }
            }

            MarginSpace(height = 15.dp)
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun EmpresaPager(empresas: List<Empresa>, navController: NavHostController) {
    val pagerState = rememberPagerState(initialPage = 0)

    HorizontalPager(state = pagerState, count = empresas.size) { page ->
        EmpresaItem(empresa = empresas[page], navController)
    }
}

@Composable
fun EmpresaItem(empresa: Empresa, navController: NavHostController) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                navController.navigate("Empresa/${empresa.idEmpresa}")
            }
    ) {
        if (!empresa.arquivoDtos.isNullOrEmpty()) {
            AsyncImage(
                model = "http://34.237.189.174/api/arquivos/exibir/${empresa.arquivoDtos?.get(0)?.id}",
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
                .padding(20.dp)
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

@Preview(showBackground = true)
@Composable
fun HomeComponentPreview() {
    val fakeEmpresaViewModel = EmpresaViewModel()

    TopHairTheme {
        HomeComponent(
            fakeEmpresaViewModel,
            rememberNavController()
        )
    }
}
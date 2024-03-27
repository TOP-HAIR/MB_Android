package com.example.tophair.app.view.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tophair.R
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.app.view.login.LoginView
import com.example.tophair.app.view.register.ui.theme.TopHairTheme

class RegisterSucessoCadastroView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopHairTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterSucessoCadastroView("Android")
                }
            }
        }
    }
}

@Composable
fun RegisterSucessoCadastroView(name: String, modifier: Modifier = Modifier) {

    val route = LocalContext.current

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val constraints = with(LocalDensity.current) { constraints }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color(4, 23, 32)
                )

        ) {

            Image(
                painter = painterResource(id = R.drawable.background_tela_inicial),
                contentDescription = "Background tela inicial",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(
                        width = constraints.maxWidth.toFloat().dp,
                        height = constraints.maxHeight.toFloat().dp
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.90f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .width(320.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(
                            id = R.drawable.logo_inicial
                        ),
                        contentDescription = "TopHair Logo",
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    MarginSpace(36.dp)

                    Text(
                        stringResource(id = R.string.titulo_tela_cadastro_sucesso),
                        fontSize = 28.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        textAlign = TextAlign.Center,
                        lineHeight = 40.sp,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold
                    )

                    MarginSpace(10.dp)
                    
                    Image(painter = painterResource(id = R.drawable.icon_success_cadastro),
                        contentDescription = "Ícone de Cadastro Bem-Sucedido",
                        modifier = Modifier.fillMaxWidth()
                            .size(width = 120.dp, height = 120.dp))

                    MarginSpace(32.dp)

                    CustomButton(text = stringResource(id = R.string.btn_txt_voltar_login),onClick = {

                        val loginView = Intent(route, LoginView::class.java)

                        route.startActivity(loginView)

                    },

                        Color(31, 116, 109, 255),

                        )


                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.txt_politicas_e_termos),
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    TopHairTheme {
        RegisterSucessoCadastroView("Android")
    }
}
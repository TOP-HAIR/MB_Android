package com.example.tophair.app.view.register

import android.content.Intent
import android.icu.text.ListFormatter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
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
import com.example.tophair.R
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.app.view.register.ui.theme.TopHairTheme

class RegisterSenhaView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopHairTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterSenhaView("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterSenhaView(name: String, modifier: Modifier = Modifier) {

    val route = LocalContext.current
    var senha by remember { mutableStateOf("") }
    var senhaConfirm by remember { mutableStateOf("") }

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
                painter = painterResource(id = R.mipmap.background_tela_inicial),
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
                            id = R.mipmap.logo_inicial
                        ),
                        contentDescription = "TopHair Logo",
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    MarginSpace(36.dp)

                    Text(
                        stringResource(
                            R.string.titulo_tela_cadastro_senha
                        ),
                        fontSize = 28.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        textAlign = TextAlign.Center,
                        lineHeight = 40.sp,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold
                    )

                    MarginSpace(32.dp)


                    OutlinedTextField(
                        value = senha,
                        onValueChange = { senha = it },
                        label = { Text(stringResource(R.string.txt_senha_confirm)) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            cursorColor = Color.Black, // Cor do cursor
                            focusedBorderColor = Color.Transparent, // Torna a borda focada transparente
                            unfocusedBorderColor = Color.Transparent // backgroundColor é definido pelo modificador 'background' abaixo
                        ),
                        modifier = Modifier
                            .background(
                                Color(0xFFCAC3DC),
                                RoundedCornerShape(32.dp)
                            ) // Cor de fundo #cac3dc
                            .fillMaxWidth() // Largura específica
                            .height(50.dp) // Altura específica
                    )

                    MarginSpace(8.dp)



                    OutlinedTextField(
                        value = senhaConfirm,
                        onValueChange = { senhaConfirm = it },
                        label = { Text(stringResource(R.string.txt_senha_confirm)) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            cursorColor = Color.Black, // Cor do cursor
                            focusedBorderColor = Color.Transparent, // Torna a borda focada transparente
                            unfocusedBorderColor = Color.Transparent // backgroundColor é definido pelo modificador 'background' abaixo
                        ),
                        modifier = Modifier
                            .background(
                                Color(0xFFCAC3DC),
                                RoundedCornerShape(32.dp)
                            ) // Cor de fundo #cac3dc
                            .fillMaxWidth() // Largura específica
                            .height(50.dp) // Altura específica
                    )

                    MarginSpace(16.dp)

                    CustomButton(stringResource(R.string.btn_txt_continue), onClick = {
                       val registerSucessoCadastroView = Intent(route, RegisterSucessoCadastroView::class.java)

                        route.startActivity(registerSucessoCadastroView)

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
fun GreetingPreview4() {
    TopHairTheme {
        RegisterSenhaView("Android")
    }
}
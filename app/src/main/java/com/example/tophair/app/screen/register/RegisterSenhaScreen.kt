package com.example.tophair.app.screen.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tophair.R
import com.example.tophair.app.data.entities.UserCadastro
import com.example.tophair.app.data.entities.UserCadastroDeserealize
import com.example.tophair.app.data.viewmodel.UserViewModel
import com.example.tophair.app.screen.menu.MenuNavigationView
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.HideSystemBars
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.ui.theme.TopHairTheme

class RegisterSenhaView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        setContent {
            TopHairTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val user = extras?.getSerializable("user") as? UserCadastro
                    RegisterSenhaScreen(user,UserViewModel())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterSenhaScreen(userParam: UserCadastro?, userViewModel: UserViewModel = UserViewModel(), modifier: Modifier = Modifier) {

    val route = LocalContext.current
    val userCadastro by userViewModel.userAtual.observeAsState()
    val erroApi by userViewModel.erroApi.observeAsState()

    val (user, userSetter) = remember { mutableStateOf(userParam)}
    var senhaConfirm by remember { mutableStateOf("") }

    HideSystemBars()

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
                    .fillMaxHeight(0.90f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .width(320.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    MarginSpace(8.dp)

                    Image(
                        painter = painterResource(
                            id = R.mipmap.logo_inicial
                        ),
                        contentDescription = "TopHair Logo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(fraction = 0.4f)
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


                    TextField(
                        value = user?.senha ?: "",
                        onValueChange = { userSetter(user?.copy(senha = it)) },
                        label = { Text(stringResource(R.string.txt_senha)) },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true
                    )


                    MarginSpace(8.dp)

                    TextField(
                        value = senhaConfirm,
                        onValueChange = { senhaConfirm = it },
                        label = { Text(stringResource(R.string.txt_senha_confirm)) },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true
                    )

                    if(user?.senha.toString() == senhaConfirm && erroApi == null && erroApi == "") {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Senhas não batem",
                            fontSize = 14.sp,
                            color = Color.Red)
                    }


                    if(erroApi != null && erroApi != "") {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = erroApi.toString(),
                            fontSize = 14.sp,
                            color = Color.Red)
                    }


                    MarginSpace(16.dp)

                    CustomButton(stringResource(R.string.btn_txt_continue), onClick = {

                        if((user?.senha.toString() == senhaConfirm)) {
                            val obj = UserCadastroDeserealize(user?.cpf.toString(), user?.nomeCompleto.toString(), user?.email.toString(), user?.senha.toString(), user?.telefone.toString(), false)

                            userViewModel.postUserCadastro(obj)

                                val registerSucessoCadastroView = Intent(route, RegisterSucessoCadastroView::class.java)
                                route.startActivity(registerSucessoCadastroView)
                        }
                      },
                      Color(31, 116, 109, 255),
                    )

                    MarginSpace(32.dp)

//                    Text(
//                        text = stringResource(R.string.txt_politicas_e_termos),
//                        fontSize = 10.sp,
//                        textAlign = TextAlign.Center,
//                        color = Color.White
//                    )
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
        RegisterSenhaScreen(null,UserViewModel())
    }
}
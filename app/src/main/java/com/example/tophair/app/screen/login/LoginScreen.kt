package com.example.tophair.app.screen.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
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
import com.example.tophair.R
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.tophair.app.data.entities.UserLogin
import com.example.tophair.app.data.service.SessionManager
import com.example.tophair.app.data.viewmodel.UserViewModel
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.app.screen.menu.MenuNavigationView
import com.example.tophair.app.utils.HideSystemBars
import com.example.tophair.app.utils.OutlinedTextFieldBackground
import com.example.tophair.ui.theme.TopHairTheme
import kotlinx.coroutines.flow.firstOrNull

class LoginView : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SessionManager.initialize(this)

        setContent {
            TopHairTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(UserViewModel())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(userViewModel: UserViewModel = UserViewModel(), modifier: Modifier = Modifier) {
    val route = LocalContext.current
    val tokenState by SessionManager.getTokenFlow().collectAsState(initial = null)
    val user by userViewModel.userAtual.observeAsState()
    val erroApi by userViewModel.erroApi.observeAsState()
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val view = LocalView.current

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

            MarginSpace(12.dp)

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

                    Image(painter = painterResource(
                        id = R.mipmap.logo_inicial),
                        contentDescription = "TopHair Logo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(fraction = 0.4f))

                    MarginSpace(36.dp)

                    Text(
                        stringResource(
                        R.string.titulo_tela_login),
                        fontSize = 28.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        textAlign = TextAlign.Center,
                        lineHeight = 40.sp,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold)

                    MarginSpace(24.dp)


                        TextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text(stringResource(R.string.txt_email)) },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .onFocusChanged { focusState ->
                                    if (focusState.isFocused) {

                                        val window = (view.context as Activity).window
                                        val insetsController =
                                            WindowCompat.getInsetsController(window, view)
                                        insetsController?.apply {
                                            hide(WindowInsetsCompat.Type.statusBars())
                                            systemBarsBehavior =
                                                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                                        }
                                    }
                                },
                            singleLine = true
                        )

                    MarginSpace(16.dp)

                    TextField(
                        value = senha,
                        onValueChange = { senha = it },
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

                    if(erroApi != null && erroApi != "") {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = erroApi.toString(),
                            fontSize = 14.sp,
                            color = Color.Red)
                    }

                    MarginSpace(16.dp)

                    CustomButton(stringResource(R.string.btn_txt_login), onClick= {
                        if(!email.isNullOrEmpty() && !senha.isNullOrEmpty()) {
                            val obj = UserLogin(email,senha)

                            userViewModel.postUserLogin(obj)
                        }
                    })


                    LaunchedEffect(tokenState) {
                        if (user != null && tokenState != null) {
                            Log.d("Token", "Token salvo: $tokenState")
                            val menuNavigationView = Intent(route, MenuNavigationView::class.java)
                            route.startActivity(menuNavigationView)
                        }
                    }

                    MarginSpace(16.dp)

                    TextButton(
                        onClick = {
                            // TODO: Implemente a lógica do que deve acontecer quando o texto for clicado
                        },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.txt_senha_reset),
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    MarginSpace(32.dp)


                }
            }

//            Column(
//                modifier = Modifier
//                    .padding(bottom = 16.dp)
//                    .align(Alignment.BottomCenter),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = stringResource(R.string.txt_politicas_e_termos),
//                    fontSize = 10.sp,
//                    textAlign = TextAlign.Center,
//                    color = Color.White
//                )
//            }

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
fun GreetingPreview3() {
    TopHairTheme {
        LoginScreen(UserViewModel())
    }
}
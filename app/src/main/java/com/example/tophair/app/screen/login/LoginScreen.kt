package com.example.tophair.app.screen.login

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.tophair.R
import com.example.tophair.app.data.entities.UserLogin
import com.example.tophair.app.data.entities.enum.TitleType
import com.example.tophair.app.data.service.SessionManager
import com.example.tophair.app.data.viewmodel.UserViewModel
import com.example.tophair.app.screen.menu.MenuNavigationView
import com.example.tophair.app.utils.*
import com.example.tophair.app.utils.fonts.TitleComposable
import com.example.tophair.ui.theme.TopHairTheme

class LoginView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        SessionManager.initialize(this)
        setContent {
            TopHairTheme {
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

@Composable
fun LoginScreen(userViewModel: UserViewModel = UserViewModel()) {
    val route = LocalContext.current
    val tokenState by SessionManager.getTokenFlow().collectAsState(initial = null)
    val user by userViewModel.userAtual.observeAsState()
    val erroApi by userViewModel.erroApi.observeAsState()
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var showModal by remember { mutableStateOf(false) }
    var modalTitle by remember { mutableStateOf("") }
    var modalMessage by remember { mutableStateOf("") }
    var iconResId by remember { mutableStateOf(R.mipmap.icon_server_error) }
    val view = LocalView.current
    val serverErrorMessage = stringResource(id = R.string.txt_modal_message_server_error)
    val credentialErrorMessage = stringResource(id = R.string.txt_modal_message_credential_error)
    val serverErrorTitle = stringResource(id = R.string.txt_modal_title_server_error)
    val credentialErrorTitle = stringResource(id = R.string.txt_modal_title_credential_error)

    LaunchedEffect(erroApi) {
        if (erroApi != null && erroApi != "") {
            showModal = true
            when (erroApi) {
                "Erro de servidor", "500" -> {
                    modalTitle = serverErrorTitle
                    modalMessage = serverErrorMessage
                    iconResId = R.mipmap.icon_server_error
                }
                "Credenciais inválidas", "400" -> {
                    modalTitle = credentialErrorTitle
                    modalMessage = credentialErrorMessage
                    iconResId = R.mipmap.icon_credential_error
                }
            }
        }
    }

    if (showModal) {
        ErrorModal(
            title = modalTitle,
            message = modalMessage,
            iconResId = iconResId,
            onDismiss = { showModal = false }
        )
    }

    RegisterComponent(
        componentContent = {
            TitleComposable(
                typeTitle = TitleType.H1,
                textTitle = stringResource(R.string.titulo_tela_login).toUpperCase(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

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

            MarginSpace(16.dp)

            CustomButton(stringResource(R.string.btn_txt_login), onClick = {
                if (!email.isNullOrEmpty() && !senha.isNullOrEmpty()) {
                    val obj = UserLogin(email, senha)
                    userViewModel.postUserLogin(obj)
                }
            })

            LaunchedEffect(tokenState) {
                if (user != null && tokenState != null) {
                    val menuNavigationView = Intent(route, MenuNavigationView::class.java)
                    route.startActivity(menuNavigationView)
                    (route as? Activity)?.overridePendingTransition(
                        R.anim.animate_slide_left_enter,
                        R.anim.animate_slide_left_exit
                    )
                }
            }

            MarginSpace(16.dp)

            TextButton(
                onClick = {
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
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    TopHairTheme {
        LoginScreen(UserViewModel())
    }
}
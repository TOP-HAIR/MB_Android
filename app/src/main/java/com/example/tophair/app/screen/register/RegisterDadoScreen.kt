package com.example.tophair.app.screen.register

import FormattedPhoneNumberTextField
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.tophair.R
import com.example.tophair.app.data.entities.UserCadastro
import com.example.tophair.app.data.entities.enum.TitleType
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.ErrorModal
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.app.utils.RegisterComponent
import com.example.tophair.app.utils.fonts.TitleComposable
import com.example.tophair.ui.theme.TopHairTheme

class RegisterDadoView : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        val extras = intent.extras
        setContent {
            TopHairTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val user = extras?.getSerializable("user") as? UserCadastro

                    RegisterDadoScreen(user)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun RegisterDadoScreen(userParam: UserCadastro?) {
    val route = LocalContext.current
    val (user, userSetter) = remember { mutableStateOf(userParam) }
    var nameError by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf("") }
    var showModal by remember { mutableStateOf(false) }
    var modalTitle by remember { mutableStateOf("") }
    var modalMessage by remember { mutableStateOf("") }
    var iconResId by remember { mutableStateOf(R.mipmap.icon_server_error) }

    val nameInvalidMessage = stringResource(R.string.txt_nome_invalido)
    val phoneInvalidMessage = stringResource(R.string.txt_telefone_invalido)

    fun removePhoneNumberMask(input: String): String {
        return input.filter { it.isDigit() }
    }

    fun validateFields(user: UserCadastro?, nameInvalidMessage: String, phoneInvalidMessage: String): Boolean {
        val isNameValid = user?.nomeCompleto?.length ?: 0 > 3
        val isPhoneValid = removePhoneNumberMask(user?.telefone ?: "").length == 11
        nameError = if (!isNameValid) nameInvalidMessage else ""
        phoneError = if (!isPhoneValid) phoneInvalidMessage else ""

        return isNameValid && isPhoneValid
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
                textTitle = stringResource(R.string.titulo_tela_cadastro_dado_pessoal).toUpperCase(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            TextField(
                value = user?.nomeCompleto ?: "",
                onValueChange = { userSetter(user?.copy(nomeCompleto = it)) },
                label = { Text(stringResource(R.string.txt_nome_completo)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true
            )

            if (nameError.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = nameError,
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }

            MarginSpace(16.dp)

            FormattedPhoneNumberTextField(
                initialValue = user?.telefone ?: "",
                onValueChange = { userSetter(user?.copy(telefone = it)) },
                modifier = Modifier.fillMaxWidth()
            )

            if (phoneError.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = phoneError,
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }

            MarginSpace(16.dp)

            CustomButton(text = stringResource(R.string.btn_txt_continue), onClick = {
                if (validateFields(user, nameInvalidMessage, phoneInvalidMessage)) {
                    val registerEnderecoView = Intent(route, RegisterEnderecoView::class.java)
                    registerEnderecoView.putExtra("user", user)

                    route.startActivity(registerEnderecoView)
                    (route as? Activity)?.overridePendingTransition(
                        R.anim.animate_slide_left_enter,
                        R.anim.animate_slide_left_exit
                    )
                }
            })

            MarginSpace(32.dp)
        }
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun RegisterDadoPreview() {
    TopHairTheme {
        RegisterDadoScreen(null)
    }
}
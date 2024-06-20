package com.example.tophair.app.screen.register

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.tophair.app.data.service.SessionManager
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.FormattedCpfTextField
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.app.utils.RegisterComponent
import com.example.tophair.app.utils.fonts.TitleComposable
import com.example.tophair.ui.theme.TopHairTheme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class RegisterEmailView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        MainScope().launch {
            SessionManager.initialize(applicationContext)
            SessionManager.clearToken()
            SessionManager.clearUserId()
        }

        setContent {
            TopHairTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterEmailScreen()
                }
            }
        }
    }
}

@Composable
fun RegisterEmailScreen() {
    val route = LocalContext.current
    val (user, userSetter) = remember { mutableStateOf(UserCadastro()) }
    var emailError by remember { mutableStateOf("") }
    var cpfError by remember { mutableStateOf("") }

    val emailInvalidMessage = stringResource(R.string.txt_email_invalido)
    val cpfInvalidMessage = stringResource(R.string.txt_cpf_invalido)

    fun removeMask(input: String): String {
        return input.filter { it.isDigit() }
    }

    fun validateEmail(email: String?): Boolean {
        return email != null && email.isNotEmpty() && email.contains("@") &&
                email.substringBefore("@").length >= 3 && email.substringAfter("@").length >= 3
    }

    fun validateFields(
        user: UserCadastro?,
        emailInvalidMessage: String,
        cpfInvalidMessage: String
    ): Boolean {
        val isEmailValid = validateEmail(user?.email)
        val isCpfValid = removeMask(user?.cpf ?: "").length == 11
        emailError = if (!isEmailValid) emailInvalidMessage else ""
        cpfError = if (!isCpfValid) cpfInvalidMessage else ""

        return isEmailValid && isCpfValid
    }

    RegisterComponent(
        componentContent = {
            TitleComposable(
                typeTitle = TitleType.H1,
                textTitle = stringResource(R.string.titulo_tela_cadastro_email).toUpperCase(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            TextField(
                value = user.email ?: "",
                onValueChange = { userSetter(user.copy(email = it)) },
                label = { Text(stringResource(R.string.txt_email)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            if (emailError.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = emailError,
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }

            MarginSpace(16.dp)

            FormattedCpfTextField(
                initialValue = user?.cpf ?: "",
                onValueChange = { userSetter(user.copy(cpf = it)) },
                modifier = Modifier.fillMaxWidth()
            )

            if (cpfError.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = cpfError,
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }

            MarginSpace(16.dp)

            CustomButton(
                stringResource(R.string.btn_txt_continue), onClick = {
                    if (validateFields(user, emailInvalidMessage, cpfInvalidMessage)) {
                        val registerDadoView = Intent(route, RegisterDadoView::class.java)
                        registerDadoView.putExtra("user", user)

                        route.startActivity(registerDadoView)
                        (route as? Activity)?.overridePendingTransition(
                            R.anim.animate_slide_left_enter,
                            R.anim.animate_slide_left_exit
                        )
                    }
                },
                color = Color(47, 156, 127)
            )

            MarginSpace(32.dp)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterEmailPreview() {
    TopHairTheme {
        RegisterEmailScreen()
    }
}
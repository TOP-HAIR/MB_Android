package com.example.tophair.app.screen.register

import android.content.Intent
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.tophair.R
import com.example.tophair.app.data.entities.UserCadastro
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.app.utils.RegisterComponent
import com.example.tophair.ui.theme.TopHairTheme

class RegisterEmailView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    RegisterComponent(
        componentContent = {
            Text(
                stringResource(
                    R.string.titulo_tela_cadastro_email
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

            Spacer(modifier = Modifier.height(18.dp))

            TextField(
                value = user.email ?: "",
                onValueChange = { userSetter(user.copy(email = it)) },
                label = { Text(stringResource(R.string.txt_email)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true
            )

            MarginSpace(16.dp)

            TextField(
                value = user.cpf ?: "",
                onValueChange = { userSetter(user.copy(cpf = it)) },
                label = { Text(stringResource(R.string.txt_cpf)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true
            )

            MarginSpace(16.dp)

            CustomButton(
                stringResource(R.string.btn_txt_continue), onClick = {
                    val registerDadoView = Intent(route, RegisterDadoView::class.java)
                    if (!user.email.isNullOrEmpty() && !user.cpf.isNullOrEmpty()) {
                        registerDadoView.putExtra("user", user)

                        route.startActivity(registerDadoView)
                    }
                },
                Color(47, 156, 127)
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
package com.example.tophair.app.screen.register

import FormattedPhoneNumberTextField
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tophair.R
import com.example.tophair.app.data.entities.UserCadastro
import com.example.tophair.app.data.entities.enum.TitleType
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.app.utils.RegisterComponent
import com.example.tophair.app.utils.fonts.TitleComposable
import com.example.tophair.ui.theme.TopHairTheme

class RegisterDadoView : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT
            )
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

            MarginSpace(16.dp)

            FormattedPhoneNumberTextField(
                initialValue = user?.telefone ?: "",
                onValueChange = { userSetter(user?.copy(telefone = it)) },
                modifier = Modifier.fillMaxWidth()
            )

            MarginSpace(16.dp)

            CustomButton(stringResource(R.string.btn_txt_continue), onClick = {
                val registerEnderecoView = Intent(route, RegisterEnderecoView::class.java)

                if (!user?.telefone.isNullOrEmpty() && !user?.nomeCompleto.isNullOrEmpty()) {
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


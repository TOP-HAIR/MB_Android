package com.example.tophair.app.screen.register

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.tophair.R
import com.example.tophair.app.data.entities.EnderecoSerializable
import com.example.tophair.app.data.entities.UserCadastro
import com.example.tophair.app.data.entities.enum.TitleType
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.FormattedCepTextField
import com.example.tophair.app.utils.FormattedCpfTextField
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.ui.theme.TopHairTheme
import com.example.tophair.app.utils.RegisterComponent
import com.example.tophair.app.utils.fonts.TitleComposable

class RegisterEnderecoView : ComponentActivity() {
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

                    RegisterEnderecoScreen(user)
                }
            }
        }
    }
}

@Composable
fun RegisterEnderecoScreen(userParam: UserCadastro?) {
    val route = LocalContext.current
    val (endereco, enderecoSetter) = remember { mutableStateOf(EnderecoSerializable()) }
    val (user) = remember { mutableStateOf(userParam) }

    RegisterComponent(
        componentContent = {
            TitleComposable(
                typeTitle = TitleType.H1,
                textTitle = stringResource(R.string.titulo_tela_cadastro_endereco).toUpperCase(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            FormattedCepTextField(
                initialValue = endereco?.cep ?: "",
                onValueChange = { enderecoSetter(endereco.copy(cep = it)) },
                modifier = Modifier.fillMaxWidth()
            )

            MarginSpace(16.dp)

            TextField(
                value = endereco?.logradouro ?: "",
                onValueChange = { enderecoSetter(endereco.copy(logradouro = it)) },
                label = { Text(stringResource(R.string.txt_logradouro)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true
            )

            MarginSpace(16.dp)

//            TextField(
//                value = endereco?.bairro ?: "",
//                onValueChange = { enderecoSetter(endereco.copy(bairro = it)) },
//                label = { Text(stringResource(R.string.txt_bairro)) },
//                keyboardOptions = KeyboardOptions.Default.copy(
//                    keyboardType = KeyboardType.Text,
//                    imeAction = ImeAction.Next
//                ),
//                modifier = Modifier
//                    .fillMaxWidth(),
//                singleLine = true
//            )

            MarginSpace(16.dp)

            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    TextField(
                        value = endereco?.numero ?: "",
                        onValueChange = { enderecoSetter(endereco.copy(numero = it)) },
                        label = { Text(stringResource(R.string.txt_numero)) },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    TextField(
                        value = endereco?.estado ?: "",
                        onValueChange = { enderecoSetter(endereco.copy(estado = it)) },
                        label = { Text(stringResource(R.string.txt_estado_endereco)) },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        singleLine = true
                    )
                }
            }

            MarginSpace(16.dp)

            TextField(
                value = endereco?.cidade ?: "",
                onValueChange = { enderecoSetter(endereco.copy(cidade = it)) },
                label = { Text(stringResource(R.string.txt_cidade)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true
            )

            MarginSpace(16.dp)

            TextField(
                value = endereco?.complemento ?: "",
                onValueChange = { enderecoSetter(endereco.copy(complemento = it)) },
                label = { Text(stringResource(R.string.txt_complemento)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true
            )

            MarginSpace(16.dp)

            CustomButton(stringResource(R.string.btn_txt_continue), onClick = {
                val registerSenhaView = Intent(route, RegisterSenhaView::class.java)

                if (!endereco?.cep.isNullOrEmpty() && !endereco?.numero.isNullOrEmpty() && !endereco?.logradouro.isNullOrEmpty() && !endereco?.estado.isNullOrEmpty() && !endereco?.cidade.isNullOrEmpty()) {
                    registerSenhaView.putExtra("user", user)
                    registerSenhaView.putExtra("endereco", endereco)

                    route.startActivity(registerSenhaView)
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

@Preview(showBackground = true)
@Composable
fun RegisterEnderecoPreview8() {
    TopHairTheme {
        RegisterEnderecoScreen(null)
    }
}
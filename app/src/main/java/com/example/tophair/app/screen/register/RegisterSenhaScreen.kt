package com.example.tophair.app.screen.register

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.tophair.R
import com.example.tophair.app.data.entities.Endereco
import com.example.tophair.app.data.entities.EnderecoSerializable
import com.example.tophair.app.data.entities.UserCadastro
import com.example.tophair.app.data.entities.UserCadastroDeserealize
import com.example.tophair.app.data.entities.enum.TitleType
import com.example.tophair.app.data.viewmodel.EnderecoViewModel
import com.example.tophair.app.data.viewmodel.UserViewModel
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.app.utils.RegisterComponent
import com.example.tophair.app.utils.fonts.TitleComposable
import com.example.tophair.app.utils.removeCepMask
import com.example.tophair.app.utils.removeCpfMask
import com.example.tophair.ui.theme.TopHairTheme
import removePhoneNumberMask

class RegisterSenhaView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = SCREEN_ORIENTATION_PORTRAIT
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
                    val endereco = extras?.getSerializable("endereco") as? EnderecoSerializable

                    RegisterSenhaScreen(user, endereco, UserViewModel(), EnderecoViewModel())
                }
            }
        }
    }
}

@Composable
fun RegisterSenhaScreen(
    userParam: UserCadastro?,
    enderecoParam: EnderecoSerializable?,
    userViewModel: UserViewModel = UserViewModel(),
    enderecoViewModel: EnderecoViewModel = EnderecoViewModel()
) {
    val route = LocalContext.current
    val userCadastro by userViewModel.userId.observeAsState()
    val userEndereco by enderecoViewModel.endereco.observeAsState()
    val erroApiUser by userViewModel.erroApi.observeAsState()
    val erroApiEndereco by enderecoViewModel.erroApi.observeAsState()

    val (user, userSetter) = remember { mutableStateOf(userParam) }
    val (endereco) = remember { mutableStateOf(enderecoParam) }
    var senhaConfirm by remember { mutableStateOf("") }

    RegisterComponent(
        componentContent = {
            TitleComposable(
                typeTitle = TitleType.H1,
                textTitle = stringResource(R.string.titulo_tela_cadastro_senha).toUpperCase(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

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

            MarginSpace(16.dp)

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

            if (user?.senha.toString() == senhaConfirm) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = stringResource(id = R.string.txt_senha_nao_bate),
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }

            if (erroApiUser != null && erroApiUser != "") {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = erroApiUser.toString(),
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }

            MarginSpace(16.dp)

            CustomButton(
                stringResource(R.string.btn_txt_continue),
                onClick = {
                    if ((user?.senha.toString() == senhaConfirm) && user?.senha?.length ?: 0 >= 8) {
                        val objUser = UserCadastroDeserealize(
                            removeCpfMask(user?.cpf.toString()),
                            user?.nomeCompleto.toString(),
                            user?.email.toString(),
                            user?.senha.toString(),
                            removePhoneNumberMask(user?.telefone.toString()),
                            false
                        )

                        userViewModel.postUserCadastro(objUser)

                        val objEndereco = Endereco(
                            endereco?.logradouro.toString(),
                            endereco?.bairro.toString(),
                            endereco?.numero.toString().toInt(),
                            endereco?.estado.toString(),
                            endereco?.complemento.toString(),
                            endereco?.cidade.toString(),
                            removeCepMask(endereco?.cep.toString()),
                        )

                        enderecoViewModel.postEndereco(objEndereco)
                    }
                },
                color = Color(31, 116, 109, 255),
            )

            LaunchedEffect(userCadastro, userEndereco) {
                if (userCadastro != null && userEndereco != null) {
                    userViewModel.putVincularUserEndereco(
                        userCadastro!!.toInt(),
                        userEndereco!!.idEndereco?.toInt()
                    )

                    val registerSucessoCadastroView =
                        Intent(route, RegisterSucessoCadastroView::class.java)
                    route.startActivity(registerSucessoCadastroView)
                    (route as? Activity)?.overridePendingTransition(
                        R.anim.animate_slide_left_enter,
                        R.anim.animate_slide_left_exit
                    )
                }
            }

            MarginSpace(32.dp)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterSenhaPreview() {
    TopHairTheme {
        RegisterSenhaScreen(null, null, UserViewModel(), EnderecoViewModel())
    }
}
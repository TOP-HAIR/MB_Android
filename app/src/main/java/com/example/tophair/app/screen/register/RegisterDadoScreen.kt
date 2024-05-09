package com.example.tophair.app.screen.register

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tophair.R
import com.example.tophair.app.data.entities.UserCadastro
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.HideSystemBars
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.ui.theme.TopHairTheme

class RegisterDadoView : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
                    RegisterDadoScreen(user)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterDadoScreen(userParam: UserCadastro?, modifier: Modifier = Modifier) {
    val route = LocalContext.current
    val (user, userSetter) = remember { mutableStateOf(userParam)}

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

                    Image(painter = painterResource(
                        id = R.mipmap.logo_inicial),
                        contentDescription = "TopHair Logo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(fraction = 0.4f))

                    MarginSpace(36.dp)

                    Text(stringResource(
                        R.string.titulo_tela_cadastro_dado_pessoal),
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

                    CustomButton(stringResource(R.string.btn_txt_continue), onClick= {
                        val registerSenhaView = Intent(route, RegisterSenhaView::class.java)
                        if(!user?.telefone.isNullOrEmpty() && !user?.nomeCompleto.isNullOrEmpty()) {
                            registerSenhaView.putExtra("user", user)
                            route.startActivity(registerSenhaView)
                        }


                    })

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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    TopHairTheme {
        RegisterDadoScreen(null)
    }
}

@Composable
fun FormattedPhoneNumberTextField(
    initialValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var textState by remember { mutableStateOf(TextFieldValue(initialValue)) }
    val focusManager = LocalFocusManager.current

    TextField(
        value = textState,
        onValueChange = { newText ->
            val formattedText = formatPhoneNumber(newText.toString())
            textState = textState.copy(text = formattedText)
            onValueChange(formattedText)
        },
        label = { Text(stringResource(R.string.txt_telefone)) },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
                focusManager.clearFocus()
            }
        ),
        singleLine = true
    )
}

fun formatPhoneNumber(input: String): String {
    val digits = input.filter { it.isDigit() }
    if (digits.isEmpty()) return ""

    val formatted = buildString {
        append("(")
        for (i in digits.indices) {
            append(digits[i])
            if (i == 1) append(") ")
            if (i == 6) append("-")
            if (i == 10) break
        }
    }
    return formatted
}
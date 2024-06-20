package com.example.tophair.app.screen.menu.tabs

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tophair.R
import com.example.tophair.app.data.entities.UserUpdate
import com.example.tophair.app.data.viewmodel.UserViewModel
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.ui.theme.TopHairTheme

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun UserComponent(userViewModel: UserViewModel) {
    val user = userViewModel.user.observeAsState().value!!

    var nomeCompleto by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    val userDataState by userViewModel.user.observeAsState()

    LaunchedEffect(userDataState) {
        userDataState?.let { user ->
            nomeCompleto = user.nomeCompleto ?: ""
            email = user.email ?: ""
            telefone = user.telefone ?: ""
        }
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center
//            ) {
//                Image(
//                    painter = painterResource(id = R.mipmap.icon_user),
//                    contentDescription = "Descrição da imagem",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(150.dp)
//                        .clip(shape = CircleShape)
//                        .shadow(4.dp, shape = CircleShape)
//                )
//            }
//
//            MarginSpace(8.dp)

            Text(
                text = stringResource(R.string.titulo_perfil_user),
                style = TextStyle(textAlign = TextAlign.Center),
                fontSize = 28.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            OutlinedTextField(
                value = nomeCompleto,
                onValueChange = { nomeCompleto = it },
                label = {
                    Text(
                        stringResource(R.string.txt_nome_completo),
                        style = TextStyle(color = Color.Black)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
                singleLine = true
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        stringResource(R.string.txt_email),
                        style = TextStyle(color = Color.Black)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
                singleLine = true
            )

            OutlinedTextField(
                value = telefone,
                onValueChange = { telefone = it },
                label = {
                    Text(
                        stringResource(R.string.txt_telefone),
                        style = TextStyle(color = Color.Black)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
                singleLine = true
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp)
                    .background(color = Color.DarkGray)
            )

            CustomButton(stringResource(R.string.btn_txt_update_user), onClick = {
                if ((nomeCompleto.isNotBlank() || email.isNotBlank() || telefone.isNotBlank())) {
                    val obj = UserUpdate(nomeCompleto, email, telefone, false)

                    val result = userViewModel.putUser(obj)
                    Log.d("putUser", "Resultado: $result")
                }
            })

            MarginSpace(8.dp)

            CustomButton(
                stringResource(R.string.btn_txt_deletar_perfil), onClick = {
                    userViewModel.deleteUser()
                },
                color = Color(0xFFDC3545)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserComponentPreview() {
    val fakeUserViewModel = UserViewModel()

    TopHairTheme {
        UserComponent(
            fakeUserViewModel
        )
    }
}
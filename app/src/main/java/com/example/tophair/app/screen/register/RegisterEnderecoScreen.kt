package com.example.tophair.app.screen.register

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tophair.R
import com.example.tophair.ui.theme.TopHairTheme
import com.example.tophair.app.utils.RegisterComponent

class RegisterEnderecoView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopHairTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterEnderecoScreen()
                }
            }
        }
    }
}

@Composable
fun RegisterEnderecoScreen() {
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



            Spacer(modifier = Modifier.height(32.dp))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterEnderecoPreview8() {
    TopHairTheme {
        RegisterEnderecoScreen()
    }
}
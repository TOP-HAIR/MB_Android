package com.example.tophair

import android.app.Activity
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.CustomRowWithDividers
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tophair.app.data.entities.enum.TitleType
import com.example.tophair.app.utils.MarginSpace
import com.example.tophair.app.screen.login.LoginView
import com.example.tophair.app.screen.register.RegisterEmailView
import com.example.tophair.app.utils.RegisterComponent
import com.example.tophair.app.utils.fonts.TitleComposable
import com.example.tophair.ui.theme.TopHairTheme


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT
            )
        )

        setContent {
            TopHairTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaInicial()
                }
            }
        }
    }
}

@Composable
fun TelaInicial() {
    val typography = MaterialTheme.typography
    val route = LocalContext.current

    RegisterComponent(
        componentContent = {
            TitleComposable(
                typeTitle = TitleType.H1,
                textTitle = stringResource(R.string.titulo_tela_inicial).toUpperCase(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            CustomButton(stringResource(R.string.btn_txt_login), onClick = {
                val loginView = Intent(route, LoginView::class.java)

                route.startActivity(loginView)
                (route as? Activity)?.overridePendingTransition(
                    R.anim.animate_slide_left_enter,
                    R.anim.animate_slide_left_exit
                )
            })

            MarginSpace(24.dp)

            CustomRowWithDividers()

            MarginSpace(24.dp)

            CustomButton(
                stringResource(R.string.btn_txt_cadastro), onClick = {
                    val registerEmailView = Intent(route, RegisterEmailView::class.java)

                    route.startActivity(registerEmailView)
                    (route as? Activity)?.overridePendingTransition(
                        R.anim.animate_slide_left_enter,
                        R.anim.animate_slide_left_exit
                    )
                },
                Color(47, 156, 127)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TelaInicialPreview() {
    TopHairTheme {
        TelaInicial()
    }
}
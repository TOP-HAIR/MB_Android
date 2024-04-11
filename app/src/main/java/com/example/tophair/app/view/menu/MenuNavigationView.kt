package com.example.tophair.app.view.menu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tophair.app.entities.enum.NavMenuEnum
import com.example.tophair.app.view.menu.tabs.CalendarComponent
import com.example.tophair.app.view.menu.tabs.HomeComponent
import com.example.tophair.app.view.menu.tabs.SearchComponent
import com.example.tophair.app.view.menu.tabs.UserComponent
import com.example.tophair.app.view.menu.ui.theme.TopHairTheme

class MenuNavigationView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopHairTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MenuNavigationView("Tela MenuNavigation", rememberNavController())
                }
            }
        }
    }
}

@Composable
fun MenuNavigationView(name: String, navController: NavHostController, modifier: Modifier = Modifier) {
    val navControllers = remember {
        NavMenuEnum.values().associateWith {
            mutableStateOf(false)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {

        NavHostWithScreens(navController = navController)

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .background(color = Color(0xFF041720))
        ) {
            NavMenuEnum.values().forEach { tela ->
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .height(90.dp)
                        .background(color = Color.Transparent)
                        .clickable {
                            navControllers.forEach { it.value.value = false };
                            navControllers[tela]?.value = true;
                            navController.navigate(tela.name)
                        }
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .background(
                                color = if (navControllers[tela]?.value == true) Color(0xFF5F8D4E) else Color(0xFF05131C)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = tela.imagem),
                            contentDescription = tela.descricao,
                            modifier = Modifier.size(40.dp)
                        )
                        Text(text = tela.nom_rota,
                            modifier = Modifier
                                .padding(4.dp),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NavHostWithScreens(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavMenuEnum.HOME.name
    ) {
        NavMenuEnum.values().forEach { tela ->
            composable(tela.name) {
                println(tela)
                when (tela) {
                    NavMenuEnum.HOME -> HomeComponent()
                    NavMenuEnum.SEARCH -> SearchComponent()
                    NavMenuEnum.CALENDAR -> CalendarComponent()
                    NavMenuEnum.USER -> UserComponent()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    TopHairTheme {
        MenuNavigationView("Tela MenuNavigation",rememberNavController())
    }
}
package com.example.tophair.app.screen.menu

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tophair.app.data.entities.enum.NavMenuEnum
import com.example.tophair.app.data.viewmodel.AgendaViewModel
import com.example.tophair.app.data.viewmodel.EmpresaViewModel
import com.example.tophair.app.data.viewmodel.ServicoViewModel
import com.example.tophair.app.data.viewmodel.UserViewModel
import com.example.tophair.app.screen.menu.tabs.CalendarComponent
import com.example.tophair.app.screen.menu.tabs.EmpresaComponent
import com.example.tophair.app.screen.menu.tabs.HomeComponent
import com.example.tophair.app.screen.menu.tabs.SearchComponent
import com.example.tophair.app.screen.menu.tabs.UserComponent
import com.example.tophair.app.utils.CustomLogo
import com.example.tophair.ui.theme.TopHairTheme

class MenuNavigationView : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private val agendaViewModel: AgendaViewModel by viewModels()
    private val empresaViewModel: EmpresaViewModel by viewModels()
    private val servicoViewModel: ServicoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )
        setContent {
            TopHairTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MenuNavigationView(
                        rememberNavController(),
                        userViewModel,
                        agendaViewModel,
                        empresaViewModel,
                        servicoViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun MenuNavigationView(
    navController: NavHostController,
    userViewModel: UserViewModel,
    agendaViewModel: AgendaViewModel,
    empresaViewModel: EmpresaViewModel,
    servicoViewModel: ServicoViewModel,
    modifier: Modifier = Modifier
) {
    val navControllers = remember {
        NavMenuEnum.values().associateWith {
            mutableStateOf(false)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        CustomLogo()

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(color = Color.White)
        ) {
            NavHostWithScreens(
                navController = navController,
                userViewModel = userViewModel,
                agendaViewModel = agendaViewModel,
                empresaViewModel = empresaViewModel,
                servicoViewModel = servicoViewModel,
            )
        }

        Row(
            modifier = Modifier
                .background(color = Color(0xFF041720))
        ) {
            NavMenuEnum.values().filter { tela ->
                tela != NavMenuEnum.EMPRESA
            }.forEach { tela ->
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
                                color = if (navControllers[tela]?.value == true) Color(0xFF5F8D4E) else Color(
                                    0xFF05131C
                                )
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = tela.imagem),
                            contentDescription = tela.descricao,
                            modifier = Modifier.size(40.dp)
                        )

                        Text(
                            text = tela.nom_rota,
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
fun NavHostWithScreens(
    navController: NavHostController,
    userViewModel: UserViewModel,
    agendaViewModel: AgendaViewModel,
    empresaViewModel: EmpresaViewModel,
    servicoViewModel: ServicoViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = NavMenuEnum.HOME.name
    ) {
        composable(NavMenuEnum.HOME.name) {
            HomeComponent(empresaViewModel, navController)
        }
        composable(NavMenuEnum.SEARCH.name) {
            SearchComponent(empresaViewModel, navController)
        }
        composable(NavMenuEnum.CALENDAR.name) {
            CalendarComponent(agendaViewModel, empresaViewModel)
        }
        composable(NavMenuEnum.USER.name) {
            UserComponent(userViewModel)
        }
        composable(
            route = "Empresa/{idEmpresa}",
            arguments = listOf(navArgument("idEmpresa") { type = NavType.IntType })
        ) { backStackEntry ->
            val idEmpresa = backStackEntry.arguments?.getInt("idEmpresa")
            if (idEmpresa != null) {
                EmpresaComponent(
                    navController = navController,
                    idEmpresa = idEmpresa,
                    servicoViewModel = servicoViewModel,
                    empresaViewModel = empresaViewModel
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuNavigationPreview() {
    val fakeUserViewModel = UserViewModel()
    val fakeAgendaViewModel = AgendaViewModel()
    val fakeEmpresaViewModel = EmpresaViewModel()
    val fakeServicoViewModel = ServicoViewModel()

    TopHairTheme {
        MenuNavigationView(
            rememberNavController(),
            fakeUserViewModel,
            fakeAgendaViewModel,
            fakeEmpresaViewModel,
            fakeServicoViewModel
        )
    }
}

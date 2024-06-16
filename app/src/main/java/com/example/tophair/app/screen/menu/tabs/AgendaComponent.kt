package com.example.tophair.app.screen.menu.tabs

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun AgendaComponent(
    navController: NavHostController,
    idServico: Int,
    idEmpresa: Int,
) {
    Text(text = "No message ${idServico} ${idEmpresa}")

}
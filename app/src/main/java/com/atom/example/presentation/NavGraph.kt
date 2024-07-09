package com.atom.example.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.atom.example.presentation.city.CitySelectScreen
import com.atom.example.presentation.select.ChargerSelectScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavRoute.CitySelectScreen.path
    ) {
        this.composable(route = NavRoute.CitySelectScreen.path) {
            CitySelectScreen(navController)
        }

        this.composable(route = NavRoute.ChargerSelectScreen.path) {
            ChargerSelectScreen(navController)
        }

    }
}

sealed class NavRoute(val path: String) {

    object CitySelectScreen : NavRoute("CitySelectScreen")

    object ChargerSelectScreen : NavRoute("ChargerSelectScreen")
}
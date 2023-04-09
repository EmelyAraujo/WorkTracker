package edu.ucne.worktracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.worktracker.ui.obras.ObraScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Rutas.Home.ruta
    ){
        composable(route = Rutas.Home.ruta) {
            HomeScreen(navController = navController)
        }
        composable(route = Rutas.RegistroObra.ruta){

            ObraScreen(navController= navController)
        }

        composable(route = Rutas.PersonaR.ruta){

        }
    }
}
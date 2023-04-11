package edu.ucne.worktracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.ucne.worktracker.ui.materiales.CardDialogList
import edu.ucne.worktracker.ui.materiales.ConsultaMaterialesScreen
import edu.ucne.worktracker.ui.obras.ObraScreen
import edu.ucne.worktracker.ui.materiales.MaterialesScreen


@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Rutas.Home.ruta
    ) {
        composable(route = Rutas.Home.ruta) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Rutas.RegistroObra.ruta + "/{id}",
        ) {
            ObraScreen(navController = navController)

        }

        composable(
            route = Rutas.RegistroMaterial.ruta + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            MaterialesScreen(navController = navController)
        }
        
        composable(
            route = Rutas.MaterialesList.ruta
        ){
            ConsultaMaterialesScreen(
                onNew = {navController.navigate(route= Rutas.RegistroMaterial.ruta)},
                navController = navController
            )
        }
        composable(route = Rutas.CardDialogListR.ruta) {
            CardDialogList(navController = navController){

            }
        }

        
    }
}







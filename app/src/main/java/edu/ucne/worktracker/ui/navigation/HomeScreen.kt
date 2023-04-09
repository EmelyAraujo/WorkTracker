package edu.ucne.worktracker.ui.navigation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.worktracker.R
import edu.ucne.worktracker.ui.obras.ObraScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
) {
    HomeScreenBody(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenBody(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Inicio", color = Color.White, fontSize = 25.sp) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(colorResource(id = R.color.blue)),
            navigationIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Filled.Menu, tint = Color.White, contentDescription = null)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false })
                {
                    DropdownMenuItem(
                        text = { Text("Registrar Obra") },
                        onClick = {
                            navController.navigate(route = Rutas.RegistroObra.ruta)
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Add,
                                contentDescription = null
                            )
                        })
                    DropdownMenuItem(
                        text = { Text("Registro de Persona") },
                        onClick = {
                            navController.navigate(route = Rutas.PersonaR.ruta) {
                                popUpTo("rutaHome")

                            }
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.People,
                                contentDescription = null
                            )
                        })
                }

            },

            actions = {
                // RowScope here, so these icons will be placed horizontally
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = "Localized description"
                    )
                }


            }
        )

        LazyColumn(

        ) {
            //TODO Contenido de la aplicacion
        }

    }
}






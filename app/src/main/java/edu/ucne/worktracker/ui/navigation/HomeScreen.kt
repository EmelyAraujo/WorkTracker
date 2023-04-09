package edu.ucne.worktracker.ui.navigation


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import edu.ucne.worktracker.R
import edu.ucne.worktracker.data.local.entity.ObraEntity
import edu.ucne.worktracker.ui.obras.ObrasViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: ObrasViewModel = hiltViewModel(),
    navController: NavHostController,

    ) {
    HomeScreenBody(
        viewModel = viewModel,
        navController = navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenBody(
    viewModel: ObrasViewModel,
    navController: NavHostController
) {
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
                        text = { Text("Registro de Materiales") },
                        onClick = {
                            navController.navigate(route = Rutas.RegistroMaterial.ruta) {
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

        val uiState by viewModel.uiState.collectAsState()
        if (uiState.obrasList == null) {
            ObraListScreen(uiState.obrasList)
        } else {
            //ObraListScreen(uiState.obrasList)
            BienvenidaScreen(navController = navController, viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BienvenidaScreen(
    viewModel: ObrasViewModel,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.CenterStart)
    ) {
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = "Bienvenidos",
            fontSize =  45.sp,
            fontStyle = FontStyle.Italic,
            color = colorResource(id = R.color.blueOpaco),
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)

        )
        Text(text = "Aun no tienes obras agregadas," +
                "EMPIEZA YA!!!!!",
            fontSize =  20.sp,
            fontStyle = FontStyle.Italic,
            color = colorResource(id = R.color.orange),
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(80.dp, 16.dp, 16.dp)
        )
        Image(painter = painterResource(id = R.drawable.wt),
            contentDescription = "Logo Inicio",
            modifier = Modifier
                .wrapContentSize(Alignment.CenterStart)
                .size(400.dp),
            alpha = 0.5f,


        )

            FloatingActionButton(
                onClick = { navController.navigate(route = Rutas.RegistroMaterial.ruta) },
                containerColor = Color(0xFFFF6500),
                modifier = Modifier.padding(300.dp,0.dp,0.dp, 20.dp),

            ) {
                Icon(Icons.Filled.Add,
                    tint = colorResource(id = R.color.white),
                    modifier =  Modifier
                        .size(35.dp),
                    contentDescription = "Localized description")
            }




    }
}


@Composable
fun ObraListScreen(obrasList: List<ObraEntity>) {
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(obrasList) { obra ->
            ObraRow(obra)

        }

    }
}

@Composable
fun ObraRow(obra: ObraEntity) {
    //TODO Implementar swipe to delete
    Card(
        shape = RoundedCornerShape(1.dp),
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.cardElevation(8.dp)

    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text(
                    text = obra.duenoObra,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(3f)
                )
            }
        }
    }

}






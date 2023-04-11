package edu.ucne.worktracker.ui.materiales

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.ucne.worktracker.R
import edu.ucne.worktracker.data.local.entity.MaterialEntity
import edu.ucne.worktracker.ui.navigation.Rutas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaMaterialesScreen(
    navController: NavHostController,
    viewModel: MaterialViewModel = hiltViewModel(),
    onNew: ()-> Unit
) {
    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Consulta de Ocupaciones") },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            },


            actions = {
                // RowScope here, so these icons will be placed horizontally
                IconButton(onClick = { navController.navigate("Home") }) {
                    Icon(Icons.Filled.Search, contentDescription = "Buscar")
                }

            }
        )
        val uiState by viewModel.uiState.collectAsState()
        MaterialesListScreen(uiState.materialesList, navController =navController )
    }

}
@Composable
fun MaterialesListScreen(materialList: List<MaterialEntity>, navController: NavHostController) {
    Column {
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = 8.dp,
                horizontal = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(materialList) { material ->
                MaterialRow(material)

            }

        }
        FloatingActionButton(
            onClick = { navController.navigate(route = Rutas.RegistroObra.ruta) },
            containerColor = Color(0xFFFF6500),
            modifier = Modifier.padding(300.dp,0.dp,0.dp, 20.dp),

            ) {
            Icon(
                Icons.Filled.Add,
                tint = colorResource(id = R.color.white),
                modifier =  Modifier
                    .size(35.dp),
                contentDescription = "Localized description")
        }

    }
}

@Composable
fun MaterialRow(
    material: MaterialEntity,
) {
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
                    text = material.fecha,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = material.descripcion,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = material.cantidad.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = material.cantRetirada.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = material.suplidor,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = material.precioUd.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(3f)
                )
            }
        }


    }

}

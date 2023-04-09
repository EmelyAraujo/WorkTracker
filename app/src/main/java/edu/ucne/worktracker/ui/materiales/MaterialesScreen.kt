package edu.ucne.worktracker.ui.materiales

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.twotone.Error
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.worktracker.R
import edu.ucne.worktracker.ui.obras.ObrasViewModel

@Composable
fun MaterialesScreen(
    viewModel: MaterialViewModel,
    navController: NavHostController
) {
    Box(Modifier.fillMaxSize()) {
        MaterialesBody(
            onDismiss = { viewModel.onDismissDialog() },
            onConfirm = { viewModel.insertar() },
            viewModel = viewModel,
            navController = navController
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialesBody(
    viewModel: MaterialViewModel,
    navController: NavHostController,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = { Text("Registro de materiales") },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("rutaHome") }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.fecha,
            onValueChange = viewModel::onMaterialesChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Person,
                    contentDescription = null,
                    tint = Color(0xFFFF6500),
                    modifier = Modifier
                        .size(45.dp)
                        .padding(4.dp)
                )
            },
            label = {
                Text(
                    text = "Fecha",
                    color = colorResource(id = R.color.blueOpaco),
                    fontStyle = FontStyle.Normal
                )
            },
            singleLine = true,
            isError = viewModel.fechaError.isNotBlank(),
            trailingIcon = {
                if (viewModel.fechaError.isNotBlank()) {
                    Icon(
                        imageVector = Icons.TwoTone.Error,
                        contentDescription = "Error en la fecha"
                    )
                }
            }
        )
        if (viewModel.fechaError.isNotBlank()) {
            Text(
                text = viewModel.fechaError,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.descripcion,
            onValueChange = viewModel::onMaterialesChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Person,
                    contentDescription = null,
                    tint = Color(0xFFFF6500),
                    modifier = Modifier
                        .size(45.dp)
                        .padding(4.dp)
                )
            },
            label = {
                Text(
                    text = "Descripción",
                    color = colorResource(id = R.color.blueOpaco),
                    fontStyle = FontStyle.Normal
                )
            },
            singleLine = true,
            isError = viewModel.descripcionError.isNotBlank(),
            trailingIcon = {
                if (viewModel.descripcionError.isNotBlank()) {
                    Icon(
                        imageVector = Icons.TwoTone.Error,
                        contentDescription = "Error en la descripcion"
                    )
                }
            }
        )
        if (viewModel.descripcionError.isNotBlank()) {
            Text(
                text = viewModel.descripcionError,
                color = MaterialTheme.colorScheme.error
            )
        }
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.cantidad,
            onValueChange = viewModel::onMaterialesChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Person,
                    contentDescription = null,
                    tint = Color(0xFFFF6500),
                    modifier = Modifier
                        .size(45.dp)
                        .padding(4.dp)
                )
            },
            label = {
                Text(
                    text = "Cantidad",
                    color = colorResource(id = R.color.blueOpaco),
                    fontStyle = FontStyle.Normal
                )
            },
            singleLine = true,
            isError = viewModel.cantidadError.isNotBlank(),
            trailingIcon = {
                if (viewModel.cantidadError.isNotBlank()) {
                    Icon(
                        imageVector = Icons.TwoTone.Error,
                        contentDescription = "Error en la cantidad"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )
        if (viewModel.cantidadError.isNotBlank()) {
            Text(
                text = viewModel.cantidadError,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.cantRetiradaError,
            onValueChange = viewModel::onMaterialesChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Person,
                    contentDescription = null,
                    tint = Color(0xFFFF6500),
                    modifier = Modifier
                        .size(45.dp)
                        .padding(4.dp)
                )
            },
            label = {
                Text(
                    text = "Cantidad Retirada",
                    color = colorResource(id = R.color.blueOpaco),
                    fontStyle = FontStyle.Normal
                )
            },
            singleLine = true,
            isError = viewModel.cantRetiradaError.isNotBlank(),
            trailingIcon = {
                if (viewModel.cantRetiradaError.isNotBlank()) {
                    Icon(
                        imageVector = Icons.TwoTone.Error,
                        contentDescription = "Error en la cantidad Retirada"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )
        if (viewModel.cantRetiradaError.isNotBlank()) {
            Text(
                text = viewModel.cantRetiradaError,
                color = MaterialTheme.colorScheme.error
            )
        }



        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = { Text("Guardar") },
            icon = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save") },
            onClick = {
                viewModel.insertar()
                onConfirm()

            }
        )
    }
}

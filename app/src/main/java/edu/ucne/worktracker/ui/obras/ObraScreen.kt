package edu.ucne.worktracker.ui.obras

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.Error
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.ucne.worktracker.R
import edu.ucne.worktracker.ui.navigation.Rutas
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObraScreen(viewModel: ObrasViewModel = hiltViewModel()) {
    Box(Modifier.fillMaxSize()) {

        DuenoObraBody(
            onDismiss = { viewModel.onDismissDialog() },
            onConfirm = { viewModel.insertar() },
            viewModel = viewModel,
            navController = rememberNavController()
        )


    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DuenoObraBody(
    viewModel: ObrasViewModel,
    navController: NavHostController,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val scope = rememberCoroutineScope()

    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(
                    1.dp,
                    color = colorResource(id = R.color.orange),
                    shape = RoundedCornerShape(15.dp)
                )

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .wrapContentSize(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    ) {

                        Icon(
                            imageVector
                            = Icons.TwoTone.ArrowBack,
                            contentDescription = null,
                            tint = Color(0xFFFF6500),
                            modifier = Modifier
                                .wrapContentSize(Alignment.CenterStart)
                                .size(35.dp)
                                .clickable {
                                    scope.launch {
                                        navController.navigate(route = Rutas.Home.ruta) {
                                        }
                                    }
                                }
                        )
                    Text(
                        text = "Registrar dueño de la obra",
                        color = colorResource(id = R.color.blueOpaco),
                        fontSize = 25.sp,
                        fontStyle = FontStyle.Italic,
                    )

                }

            }
            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                value = viewModel.duenoObra,
                onValueChange = viewModel::onDuenoObraChanged,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.TwoTone.Person,
                        contentDescription = null,
                        tint = Color(0xFFFF6500),
                        modifier = Modifier
                            .size(30.dp)
                            .padding(4.dp)
                    )
                },
                label = { Text("Dueño de la obra") },
                singleLine = true,
                isError = viewModel.duenoObraError.isNotBlank(),
                trailingIcon = {
                    if (viewModel.duenoObraError.isNotBlank()) {
                        Icon(
                            imageVector = Icons.TwoTone.Error,
                            contentDescription = "Error en la descripcion"
                        )
                    }
                }
            )
            if (viewModel.duenoObraError.isNotBlank()) {
                Text(
                    text = viewModel.duenoObraError,
                    color = MaterialTheme.colorScheme.error
                )
            }

            ExtendedFloatingActionButton(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = { Text("Registrar") },
                icon = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save") },
                onClick = {
                    navController.navigate(route = Rutas.Home.ruta)
                    onConfirm()
                }
            )

        }


    }
}

}




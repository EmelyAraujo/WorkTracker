package edu.ucne.worktracker.ui.materiales

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.Error
import androidx.compose.material.icons.twotone.PersonAddAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import edu.ucne.worktracker.R
import edu.ucne.worktracker.ui.navigation.Rutas
import edu.ucne.worktracker.ui.obras.ObrasViewModel
import kotlinx.coroutines.launch

@OptIn( ExperimentalComposeUiApi::class)
@Composable
fun CardDialogList(
    navController: NavHostController,
    onDismiss: () -> Unit,
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
                                        navController.navigate("rutaHome") {

                                        }
                                    }
                                }
                        )
                        Text(
                            text = "A donde desea hacer?",
                            color = colorResource(id = R.color.blueOpaco),
                            fontSize = 25.sp,
                            fontStyle = FontStyle.Italic,
                        )

                    }

                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            navController.navigate(route = Rutas.RegistroMaterial.ruta)
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFFFF6500),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Agregar materiales",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Button(
                        onClick = {
                            navController.navigate(route = Rutas.MaterialesList.ruta)
                        },
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFF004E98),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Ver Lista",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
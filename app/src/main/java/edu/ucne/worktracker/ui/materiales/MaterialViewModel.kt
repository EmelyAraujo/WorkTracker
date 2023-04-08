package edu.ucne.worktracker.ui.materiales

import edu.ucne.worktracker.data.local.entity.MaterialEntity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.worktracker.data.repository.MaterialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MaterialUiState(
    val materialesList: List<MaterialEntity> = emptyList()
)

@HiltViewModel
class MaterialViewModel @Inject constructor(
    private val materialRepository: MaterialRepository
) : ViewModel() {
    var obraId by mutableStateOf("")
    var fecha by mutableStateOf("")
    var descripcion by mutableStateOf("")
    var cantidad by mutableStateOf("")
    var cantRetirada by mutableStateOf("")
    var suplidor by mutableStateOf("")
    var precioUd by mutableStateOf(" ")

    var uiState = MutableStateFlow(MaterialUiState())
        private set

    init {
        getLista()
    }

    fun getLista() {
        viewModelScope.launch(Dispatchers.IO) {
            materialRepository.getList().collect{lista ->
                uiState.update {
                    it.copy(materialesList = lista)
                }
            }
        }
    }

    fun insertar() {
        val material = MaterialEntity(
            obraId = obraId.toIntOrNull() ?: 0,
            fecha = fecha,
            descripcion = descripcion,
            cantidad = cantidad.toIntOrNull() ?: 0,
            cantRetirada = cantRetirada.toIntOrNull() ?: 0,
            suplidor = suplidor,
            precioUd = precioUd.toDoubleOrNull() ?: 0.0
        )

        viewModelScope.launch(Dispatchers.IO) {
            materialRepository.insert(material)
            Limpiar()
        }
    }

    private fun Limpiar() {
        obraId = ""
        fecha = ""
        descripcion = ""
        cantidad = ""
        cantRetirada = ""
        suplidor = ""
        precioUd= ""
    }

}
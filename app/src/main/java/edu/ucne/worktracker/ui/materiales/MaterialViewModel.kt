package edu.ucne.worktracker.ui.materiales

import edu.ucne.worktracker.data.local.entity.MaterialEntity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.worktracker.data.local.entity.ObraEntity
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
    var isDialogShown by mutableStateOf(false)
    var materiales by  mutableStateOf("")

    var obraId by mutableStateOf("")
    var obraIdError by mutableStateOf("")

    var fecha by mutableStateOf("")
    var fechaError by mutableStateOf("")

    var descripcion by mutableStateOf("")
    var descripcionError by mutableStateOf("")

    var cantidad by mutableStateOf("")
    var cantidadError by mutableStateOf("")

    var cantRetirada by mutableStateOf("")
    var cantRetiradaError by mutableStateOf("")

    var suplidor by mutableStateOf("")
    var suplidorError by mutableStateOf("")

    var precioUd by mutableStateOf(" ")
    var precioUdError by mutableStateOf("")

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
    private fun hayError(): Boolean{
        var hayError = true
        obraIdError=""
        fechaError = ""
        descripcionError = ""
        cantidadError = ""
        cantRetiradaError = ""
        suplidorError = ""
        precioUdError = ""
        if(obraIdError.isNullOrBlank()){
            obraIdError = "Debe ingresar algun dato"
            hayError = false
        }
        if(fechaError.isNullOrBlank()){
            fechaError = "Debe ingresar algun dato"
            hayError = false
        }
        if(descripcionError.isNullOrBlank()){
            descripcionError = "Debe ingresar algun dato"
            hayError = false
        }
        if(cantidadError.isNullOrBlank()){
            cantidadError = "Debe ingresar algun dato"
            hayError = false
        }
        if(cantRetiradaError.isNullOrBlank()){
            cantRetiradaError = "Debe ingresar algun dato"
            hayError = false
        }
        if(suplidorError.isNullOrBlank()){
            suplidorError = "Debe ingresar algun dato"
            hayError = false
        }
        if(precioUdError.isNullOrBlank()){
            precioUdError = "Debe ingresar algun dato"
            hayError = false
        }

        return hayError
    }

    fun onMaterialesChanged(materiales: String){
        this.materiales = materiales
        hayError()
    }


    fun insertar() {
        val material = MaterialEntity(
            obraId = obraId.toInt(),
            fecha = fecha,
            descripcion = descripcion,
            cantidad = cantidad.toInt(),
            cantRetirada = cantRetirada.toInt() ,
            suplidor = suplidor,
            precioUd = precioUd.toDouble()
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
    fun onDismissDialog(){
        isDialogShown = false
    }

    fun Eliminar() {
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
            materialRepository.delete(material)
            Limpiar()
        }
    }

}
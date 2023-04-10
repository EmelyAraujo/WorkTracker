package edu.ucne.worktracker.ui.materiales

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.worktracker.Util.Resource
import edu.ucne.worktracker.data.local.entity.MaterialEntity
import edu.ucne.worktracker.data.remote.dto.MaterialDto
import edu.ucne.worktracker.data.repository.MaterialRepository
import edu.ucne.worktracker.data.repository.MaterialRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MaterialListUiState(
    val estaCargando: Boolean = false, //Is Loading
    val material: List<MaterialDto> = emptyList(), //Success
    val mensajeError: String? = null //Error
)

data class MaterialesUiState(
    val estaCargando: Boolean = false,
    val material : MaterialDto? = null,
    val mensajeError: String? = null
)

@HiltViewModel
class MaterialViewModelApi @Inject constructor(
    private val materialRepositoryImp: MaterialRepositoryImp
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

    var materialId by mutableStateOf(0)

    var listUiState = MutableStateFlow(MaterialListUiState())
        private set


    var uiState = MutableStateFlow(MaterialesUiState())
        private set

    init {
        materialRepositoryImp.getMaterial().onEach { resultado ->
            when (resultado) {
                is Resource.Loading -> {
                    listUiState.update { materialList ->
                        materialList.copy(estaCargando = true)
                    }
                }
                is Resource.Success -> {
                    listUiState.update{ MaterialList ->
                        MaterialList.copy(material=  resultado.data ?: emptyList(), estaCargando = false)
                    }
                }
                is Resource.Error -> {
                    listUiState.update { materiaList ->
                        materiaList.copy(mensajeError = resultado.message)
                    }
                }
            }
        }.launchIn(viewModelScope)
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
        if(materiales.isNullOrBlank()){
            materiales = "Debe ingresar algun dato"
            hayError = false
        }

        return hayError
    }

    fun onMaterialesChanged(materiales: String){
        this.materiales = materiales
        hayError()
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



    fun putMaterial(){
        viewModelScope.launch {
            materialRepositoryImp.putMaterial(materialId,
                MaterialDto(

                    obraId = obraId.toIntOrNull() ?: 0,
                    fecha = fecha,
                    descripcion = descripcion,
                    cantidad = cantidad.toIntOrNull() ?: 0,
                    cantRetirada = cantRetirada.toIntOrNull() ?: 0,
                    suplidor = suplidor,
                    precioUd = precioUd.toDoubleOrNull() ?: 0.0 ,
                    materialId = materialId

                )
            )
        }
    }

    fun postMaterial(){
        viewModelScope.launch {
            materialRepositoryImp.postMaterial(
                MaterialDto(

                    obraId = obraId.toIntOrNull() ?: 0,
                    fecha = fecha,
                    descripcion = descripcion,
                    cantidad = cantidad.toIntOrNull() ?: 0,
                    cantRetirada = cantRetirada.toIntOrNull() ?: 0,
                    suplidor = suplidor,
                    precioUd = precioUd.toDoubleOrNull() ?: 0.0 ,
                    materialId = materialId
                )
            )
        }
    }
    fun deleteMaterial(){
        viewModelScope.launch {
            materialRepositoryImp.deleteMaterial(materialId,
                MaterialDto(

                    obraId = obraId.toIntOrNull() ?: 0,
                    fecha = fecha,
                    descripcion = descripcion,
                    cantidad = cantidad.toIntOrNull() ?: 0,
                    cantRetirada = cantRetirada.toIntOrNull() ?: 0,
                    suplidor = suplidor,
                    precioUd = precioUd.toDoubleOrNull() ?: 0.0 ,
                    materialId = materialId

                )
            )
        }
    }

}
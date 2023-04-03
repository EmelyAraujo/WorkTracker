package edu.ucne.worktracker.ui.Materiales

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucne.worktracker.Util.Resource
import edu.ucne.worktracker.data.Repository.MaterialRepositoy
import edu.ucne.worktracker.data.remote.dto.MaterialDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

data class MaterialListUiState(
    val estaCargando: Boolean = false, //Is Loading
    val material: List<MaterialDto> = emptyList(), //Success
    val mensajeError: String? = null //Error
)

data class MaterialUiState(
    val estaCargando: Boolean = false,
    val material: MaterialDto? = null,
    val mensajeError: String? = null
)

class MaterialViewModel @Inject constructor(

    private val materialRepos: MaterialRepositoy
) : ViewModel() {

    var materialId by mutableStateOf(0)
    var duenoObra by mutableStateOf("")
    var fecha by mutableStateOf("")
    var descripcion by mutableStateOf("")
    var cantidad by mutableStateOf("")
    var cantRetirada by mutableStateOf("")
    var suplidor by mutableStateOf("")
    var precioUd by mutableStateOf("")

    /* ESTADO DE LA LISTA */
    var listUiState = MutableStateFlow(MaterialListUiState())
        private set

    /* ESTADO DE UN SOLO Material */

    var uiState = MutableStateFlow(MaterialUiState())
        private set

    init {
        materialRepos.getMaterial().onEach { resultado -> //resultado de la llamada a la API
            when (resultado) {
                is Resource.Loading -> {
                    listUiState.update { materialList ->
                        materialList.copy(estaCargando = true)
                    }
                }
                is Resource.Success -> {
                    listUiState.update { materialList ->
                        materialList.copy(
                            material = resultado.data ?: emptyList(),
                            estaCargando = false
                        )
                    }
                }
                is Resource.Error -> {
                    listUiState.update { materialList ->
                        materialList.copy(mensajeError = resultado.message)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteMaterial(id: Int) {
        viewModelScope.launch {
            materialId = id!!
            try {
                if (materialId != null) {
                    materialRepos.deleteMaterial(materialId)
                } else {
                    throw NullPointerException("Value is null")
                }
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
    }

    fun findTicket(id: Int) {
        materialId = id
        limpiar()
        materialRepos.getMaterialById(id).onEach { resultado -> //resultado de la llamada a la API
            when (resultado) {
                is Resource.Loading -> {
                    uiState.update { estado ->
                        estado.copy(estaCargando = true)
                    }
                }
                is Resource.Success -> {
                    uiState.update { estado ->

                        /*ALMACENAR LO CAMPOS DE LA API TEMPORALMENTE*/
                        estado.copy(material = resultado.data, estaCargando = false)
                    }
                    /*ACTUALIZAR LOS CAMPOS QUE SE VISUALIZAN EN LA UI*/

                    duenoObra = uiState.value.material!!.duenoObra
                    /*fecha= uiState.value.material!!.fecha*/
                    descripcion = uiState.value.material!!.descripcion
                    /* cantidad = uiState.value.material!!.catidad*/
                    /* cantRetirada= uiState.value.material!!.cantRetirada*/
                    suplidor = uiState.value.material!!.suplidor
                    /*precioUd= uiState.value.material!!.precioUd*/

                }
                is Resource.Error -> {
                    uiState.update { estado ->
                        estado.copy(mensajeError = resultado.message ?: "ERROR")
                    }
                }
            }
        }.launchIn(viewModelScope)
    }


    fun putTicket(){
        viewModelScope.launch {
            materialRepos.putMaterial(materialId,
                MaterialDto (
                    duenoObra= duenoObra,
                    fecha = uiState.value.material!!.fecha,
                    descripcion = descripcion,
                    catidad = uiState.value.material!!.catidad,
                    cantRetirada = uiState.value.material!!.cantRetirada,
                    suplidor = suplidor,
                    precioUd=uiState.value.material!!.precioUd,
                    materialId = materialId
                )
            )
        }
    }

    private fun limpiar() {
        duenoObra = ""
        fecha = ""
        descripcion = ""
        cantidad = ""
        cantRetirada = ""
        suplidor  = ""
        precioUd = ""
    }
}

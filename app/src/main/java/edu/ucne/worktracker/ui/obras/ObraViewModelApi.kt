package edu.ucne.worktracker.ui.obras


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.worktracker.Util.Resource
import edu.ucne.worktracker.data.remote.dto.ObraDto
import edu.ucne.worktracker.data.repository.ObraRepositoryImp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ObraListUiState(
    val estaCargando: Boolean = false, //Is Loading
    val obra: List<ObraDto> = emptyList(), //Success
    val mensajeError: String? = null //Error
)

data class ObraUiState(
    val estaCargando: Boolean = false,
    val obra : ObraDto? = null,
    val mensajeError: String? = null
)

@HiltViewModel
class ObraViewModelApi @Inject constructor(
    private val obraRepositoryImp: ObraRepositoryImp
) : ViewModel() {

    var isDialogShown by mutableStateOf(false)
    var duenoObra by mutableStateOf("")
    var duenoObraError by mutableStateOf("")
    var obra by  mutableStateOf("")
    var listUiState = MutableStateFlow(ObraListUiState())
        private set

    var uiState = MutableStateFlow(ObraUiState())
        private set

    var obraId by mutableStateOf(0)



    init {
        obraRepositoryImp.getObra().onEach { resultado ->
            when (resultado) {
                is Resource.Loading -> {
                    listUiState.update { obralList ->
                        obralList.copy(estaCargando = true)
                    }
                }
                is Resource.Success -> {
                    listUiState.update{ obraList ->
                        obraList.copy(obra=  resultado.data ?: emptyList(), estaCargando = false)
                    }
                }
                is Resource.Error -> {
                    listUiState.update { obraList ->
                        obraList .copy(mensajeError = resultado.message)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun hayError(): Boolean{
        var hayError = true
        duenoObraError = ""

        if(obra.isNullOrBlank()){
            obra= "Debe ingresar algun dato"
            hayError = false
        }

        return hayError
    }
    fun onMaterialesChanged(obra: String){
        this.obra= obra
        hayError()
    }


    fun putObra(){
        viewModelScope.launch {
            obraRepositoryImp.putObra(obraId,
                ObraDto(

                    obraId = obraId,
                    duenoObra = duenoObra

                )
            )
        }
    }

    fun postObra(){
        viewModelScope.launch {
            obraRepositoryImp.postObra(
                ObraDto(

                    obraId = obraId,
                    duenoObra = duenoObra
                )
            )
        }
    }

    fun deleteMaterial(){
        viewModelScope.launch {
            obraRepositoryImp.deleteObra(obraId,
                ObraDto(

                    obraId = obraId ,
                    duenoObra = duenoObra
                )
            )
        }
    }

    private fun Limpiar() {
        duenoObra = ""

    }
    fun onDismissDialog(){
        isDialogShown = false
    }


}
package edu.ucne.worktracker.ui.obras

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.worktracker.data.local.entity.ObraEntity
import edu.ucne.worktracker.data.repository.ObraRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ObrasUiState(
    val obrasList: List<ObraEntity> = emptyList()
)

@HiltViewModel
class ObrasViewModel @Inject constructor(
    private val obraRepository: ObraRepository
) : ViewModel() {
    var duenoObra by mutableStateOf("")


    var uiState = MutableStateFlow(ObrasUiState())
        private set

    init {
        getLista()
    }

    fun getLista() {
        viewModelScope.launch(Dispatchers.IO) {
            obraRepository.getList().collect{lista ->
                uiState.update {
                    it.copy(obrasList = lista)
                }
            }
        }
    }

    fun insertar() {
        val obra = ObraEntity(
            duenoObra = duenoObra,
        )

        viewModelScope.launch(Dispatchers.IO) {
            obraRepository.insert(obra)
            Limpiar()
        }
    }

    private fun Limpiar() {
        duenoObra = ""
    }

}
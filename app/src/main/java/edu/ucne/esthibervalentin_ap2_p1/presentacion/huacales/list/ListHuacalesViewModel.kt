package edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.esthibervalentin_ap2_p1.domain.usecases.huacales.DeleteHuacalesUseCase
import edu.ucne.esthibervalentin_ap2_p1.domain.usecases.huacales.ObserveHuacalesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListHuacalesViewModel @Inject constructor(
    private val observeHuacalesUseCase: ObserveHuacalesUseCase,
    private val deleteHuacalesUseCase: DeleteHuacalesUseCase
): ViewModel(){
    private val _state = MutableStateFlow(ListHuacalesUiState(isLoading = true))
    val state: StateFlow<ListHuacalesUiState> = _state.asStateFlow()

    init{
        onEvent(ListHuacalesUiEvent.Load)
    }

    fun onEvent(event: ListHuacalesUiEvent){
        when(event){
            ListHuacalesUiEvent.Load -> observe()
            is ListHuacalesUiEvent.Delete -> onDelete(event.id)
            ListHuacalesUiEvent.CreateNew -> _state.value = _state.value.copy(navigationToCreate = true)
            is ListHuacalesUiEvent.Edit -> _state.value = _state.value.copy(navigateToEditId = event.id)
            is ListHuacalesUiEvent.ShowMessage -> _state.value = _state.value.copy(message = event.message)
        }
    }

    private fun observe(){
        viewModelScope.launch {
            observeHuacalesUseCase().collect{ list ->
                _state.value = _state.value.copy(isLoading = false, huacales = list, message = null)
            }
        }
    }

    private fun onDelete(id: Int){
        viewModelScope.launch {
            when (val result = deleteHuacalesUseCase(id)){
                is DeleteHuacalesUseCase.DeleteResult.Success -> {
                    _state.value = _state.value.copy(message = "Huacal eliminado")
                }
                is DeleteHuacalesUseCase.DeleteResult.Error -> {
                    _state.value = _state.value.copy(message = "Error al eliminar el huacal: ${result.message?:""}")
                }
            }
        }
    }

    fun onNavigationHandled(){
        _state.value = _state.value.copy(navigationToCreate = false, navigateToEditId = null)
    }
}
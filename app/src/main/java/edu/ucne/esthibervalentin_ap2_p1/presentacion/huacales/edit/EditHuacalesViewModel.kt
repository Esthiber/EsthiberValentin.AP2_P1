package edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.esthibervalentin_ap2_p1.domain.model.Huacales
import edu.ucne.esthibervalentin_ap2_p1.domain.usecases.huacales.DeleteHuacalesUseCase
import edu.ucne.esthibervalentin_ap2_p1.domain.usecases.huacales.GetHuacalesByIdUseCase
import edu.ucne.esthibervalentin_ap2_p1.domain.usecases.huacales.UpsertHuacalesUseCase
import edu.ucne.esthibervalentin_ap2_p1.domain.usecases.huacales.validateCantidad
import edu.ucne.esthibervalentin_ap2_p1.domain.usecases.huacales.validateNombreCliente
import edu.ucne.esthibervalentin_ap2_p1.domain.usecases.huacales.validatePrecio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EditHuacalesViewModel @Inject constructor(
    private val getHuacalesByIdUseCase: GetHuacalesByIdUseCase,
    private val upsertHuacalesUseCase: UpsertHuacalesUseCase,
    private val deleteHuacalesUseCase: DeleteHuacalesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(value = EditHuacalesUiState())
    val state: MutableStateFlow<EditHuacalesUiState> = _state

    fun onEvent(event: EditHuacalesUiEvent) {
        when (event) {
            is EditHuacalesUiEvent.Load -> onLoad(event.id)
            is EditHuacalesUiEvent.NombreClienteChanged -> _state.value =
                _state.value.copy(nombreCliente = event.value, nombreClienteError = null)

            is EditHuacalesUiEvent.CantidadChanged -> _state.value =
                _state.value.copy(cantidad = event.value.toIntOrNull() ?: 0, cantidadError = null)

            is EditHuacalesUiEvent.PrecioChanged -> _state.value =
                _state.value.copy(precio = event.value.toDoubleOrNull() ?: 0.0, precioError = null)

            is EditHuacalesUiEvent.FechaChanged -> _state.value =
                _state.value.copy(fecha = event.value)

            is EditHuacalesUiEvent.Save -> onSave()
            is EditHuacalesUiEvent.Delete -> onDelete()
            is EditHuacalesUiEvent.Reset -> onReset()
        }
    }

    private fun onReset() {
        _state.value = EditHuacalesUiState()
    }

    private fun onLoad(id: Int?) {
        onReset()

        if (id == null || id == 0) {
            _state.value = _state.value.copy(
                isNew = true,
                IdEntrada = null,
                fecha = Date().toString()
            )
            return
        }
        viewModelScope.launch {
            val huacal = getHuacalesByIdUseCase(id)
            if (huacal != null) {
                _state.update {
                    it.copy(
                        isNew = false,
                        IdEntrada = huacal.IdEntrada,
                        nombreCliente = huacal.NombreCliente,
                        cantidad = huacal.Cantidad,
                        precio = huacal.Precio,
                        fecha = huacal.Fecha,
                        saved = false,
                        deleted = false
                    )
                }
            }
        }
    }

    private fun onSave() {
        val nombreClienteError = validateNombreCliente(_state.value.nombreCliente)
        val cantidadError = validateCantidad(_state.value.cantidad.toString())
        val precioError = validatePrecio(_state.value.precio.toString())

        if (!nombreClienteError.isValid || !cantidadError.isValid || !precioError.isValid) {
            _state.update {
                it.copy(
                    nombreClienteError = nombreClienteError.error,
                    cantidadError = cantidadError.error,
                    precioError = precioError.error,
                    isSaving = false,
                    saved = false
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            val id = state.value.IdEntrada ?: 0

            if (state.value.fecha.isNullOrBlank()) {
                _state.value = _state.value.copy(fecha = Date().toString())
            }

            val huacal = Huacales(
                IdEntrada = id,
                Fecha = state.value.fecha,
                NombreCliente = state.value.nombreCliente,
                Cantidad = state.value.cantidad,
                Precio = state.value.precio
            )
            val result = upsertHuacalesUseCase(huacal)
            result.onSuccess { newId ->
                _state.value = EditHuacalesUiState(saved = true, IdEntrada = newId)
            }.onFailure { e ->
                _state.update { it.copy(isSaving = false) }
            }

        }
    }

    private fun onDelete() {
        val IdEntrada = _state.value.IdEntrada
        if (IdEntrada == null || IdEntrada == 0) {
            _state.value = _state.value.copy(deleted = true)
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isDeleting = true) }
            deleteHuacalesUseCase(IdEntrada)
            _state.update { it.copy(isDeleting = false, deleted = true) }
        }
    }
}
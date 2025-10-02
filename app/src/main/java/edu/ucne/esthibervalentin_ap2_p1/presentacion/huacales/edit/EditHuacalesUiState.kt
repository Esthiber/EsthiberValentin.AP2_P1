package edu.ucne.esthibervalentin_ap2_p1.presentacion.huacales.edit

data class EditHuacalesUiState(
    val IdEntrada: Int? = null,
    val nombreCliente: String = "",
    val cantidad: Int = 0,
    val precio: Double = 0.0,
    val fecha: String = "",

    val nombreClienteError: String? = null,
    val cantidadError: String? = null,
    val precioError: String? = null,

    val isSaving: Boolean = false,
    val isDeleting: Boolean = false,
    val isNew: Boolean = true,
    val saved: Boolean = false,
    val deleted: Boolean = false
)

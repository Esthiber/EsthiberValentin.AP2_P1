package edu.ucne.esthibervalentin_ap2_p1.domain.model

data class Huacales(
    val IdEntrada: Int = 0,
    val Fecha: String,
    val NombreCliente: String,
    val Cantidad: Int,
    val Precio: Double,
)
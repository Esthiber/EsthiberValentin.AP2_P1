package edu.ucne.esthibervalentin_ap2_p1.data.huacales.mapper

import edu.ucne.esthibervalentin_ap2_p1.data.huacales.local.HuacalesEntity
import edu.ucne.esthibervalentin_ap2_p1.domain.model.Huacales

fun HuacalesEntity.toDomain(): Huacales = Huacales(
    IdEntrada = IdEntrada,
    Fecha = Fecha,
    NombreCliente = NombreCliente,
    Cantidad = Cantidad,
    Precio = Precio
)

fun Huacales.toEntity(): HuacalesEntity = HuacalesEntity(
    IdEntrada = IdEntrada,
    Fecha = Fecha,
    NombreCliente = NombreCliente,
    Cantidad = Cantidad,
    Precio = Precio
)
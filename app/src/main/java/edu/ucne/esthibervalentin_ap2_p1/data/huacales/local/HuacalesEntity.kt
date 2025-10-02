package edu.ucne.esthibervalentin_ap2_p1.data.huacales.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "huacales")
class HuacalesEntity(
    @PrimaryKey(autoGenerate = true)
    val IdEntrada: Int = 0,
    val Fecha: String,
    val NombreCliente: String,
    val Cantidad: Int,
    val Precio: Double,
)

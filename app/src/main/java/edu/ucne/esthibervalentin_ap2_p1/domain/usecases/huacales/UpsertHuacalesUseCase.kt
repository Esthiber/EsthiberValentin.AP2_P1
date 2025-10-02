package edu.ucne.esthibervalentin_ap2_p1.domain.usecases.huacales

import edu.ucne.esthibervalentin_ap2_p1.domain.model.Huacales
import edu.ucne.esthibervalentin_ap2_p1.domain.repository.HuacalesRepository
import javax.inject.Inject

class UpsertHuacalesUseCase @Inject constructor(
    private val repository: HuacalesRepository
){
    suspend operator fun invoke(huacal: Huacales): Result<Int> {
        val nombreClienteResult = validateNombreCliente(huacal.NombreCliente)
        val cantidadResult = validateCantidad(huacal.Cantidad.toString())
        val precioResult = validatePrecio(huacal.Precio.toString())

        if (!nombreClienteResult.isValid) {
            return Result.failure(IllegalArgumentException(nombreClienteResult.error))
        }

        if (!cantidadResult.isValid) {
            return Result.failure(IllegalArgumentException(cantidadResult.error))
        }

        if (!precioResult.isValid) {
            return Result.failure(IllegalArgumentException(precioResult.error))
        }
        return runCatching { repository.upsert(huacal) }
    }
}
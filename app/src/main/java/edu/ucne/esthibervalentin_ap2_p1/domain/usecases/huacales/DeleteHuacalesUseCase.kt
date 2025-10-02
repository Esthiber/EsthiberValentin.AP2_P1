package edu.ucne.esthibervalentin_ap2_p1.domain.usecases.huacales

import edu.ucne.esthibervalentin_ap2_p1.domain.repository.HuacalesRepository
import javax.inject.Inject

class DeleteHuacalesUseCase @Inject constructor(
    private val repository: HuacalesRepository
){
    sealed class DeleteResult {
        object Success : DeleteResult()
        data class Error(val message: String) : DeleteResult()
    }

    suspend operator fun invoke(id: Int): DeleteResult {
        return try {
            repository.deleteById(id)
            DeleteResult.Success
        } catch (e: Exception) {
            DeleteResult.Error(e.message ?: "Error desconocido al eliminar huacal")
        }
    }
}

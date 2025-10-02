package edu.ucne.esthibervalentin_ap2_p1.domain.usecases.huacales

import edu.ucne.esthibervalentin_ap2_p1.domain.model.Huacales
import edu.ucne.esthibervalentin_ap2_p1.domain.repository.HuacalesRepository
import javax.inject.Inject

class GetHuacalesByIdUseCase @Inject constructor(
    private val repository: HuacalesRepository
) {
    suspend operator fun invoke(id: Int?): Huacales? = repository.getById(id)
}
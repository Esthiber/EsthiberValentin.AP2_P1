package edu.ucne.esthibervalentin_ap2_p1.domain.usecases.huacales

import edu.ucne.esthibervalentin_ap2_p1.domain.model.Huacales
import edu.ucne.esthibervalentin_ap2_p1.domain.repository.HuacalesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveHuacalesUseCase @Inject constructor(
    private val repository: HuacalesRepository
){
    operator fun invoke(): Flow<List<Huacales>> = repository.observeAll()
}
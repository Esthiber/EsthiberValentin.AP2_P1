package edu.ucne.esthibervalentin_ap2_p1.domain.repository

import edu.ucne.esthibervalentin_ap2_p1.domain.model.Huacales
import kotlinx.coroutines.flow.Flow

interface HuacalesRepository {
    fun observeAll(): Flow<List<Huacales>>
    suspend fun getById(id: Int?): Huacales?
    suspend fun upsert(huacales: Huacales): Int
    suspend fun delete(huacales: Huacales)
    suspend fun deleteById(id: Int)
}
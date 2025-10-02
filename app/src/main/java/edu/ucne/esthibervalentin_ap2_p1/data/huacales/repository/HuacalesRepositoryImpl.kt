package edu.ucne.esthibervalentin_ap2_p1.data.huacales.repository

import edu.ucne.esthibervalentin_ap2_p1.data.huacales.local.HuacalesDao
import edu.ucne.esthibervalentin_ap2_p1.data.huacales.mapper.toDomain
import edu.ucne.esthibervalentin_ap2_p1.data.huacales.mapper.toEntity
import edu.ucne.esthibervalentin_ap2_p1.domain.model.Huacales
import edu.ucne.esthibervalentin_ap2_p1.domain.repository.HuacalesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HuacalesRepositoryImpl @Inject constructor(
    private val dao: HuacalesDao
): HuacalesRepository {
    override fun observeAll(): Flow<List<Huacales>> = dao.ObserveAll().map {
        list -> list.map { it.toDomain()}
    }
    override suspend fun getById(id: Int?): Huacales? = dao.getById(id)?.toDomain()

    override suspend fun upsert(huacales: Huacales): Int {
        dao.upsert(huacales.toEntity())
        return huacales.IdEntrada
    }

    override suspend fun delete(huacales: Huacales) {
        dao.delete(huacales.toEntity())
    }

    override suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }

}
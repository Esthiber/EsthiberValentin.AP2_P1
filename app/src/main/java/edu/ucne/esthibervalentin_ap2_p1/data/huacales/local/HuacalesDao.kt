package edu.ucne.esthibervalentin_ap2_p1.data.huacales.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface HuacalesDao {
    @Query("SELECT * FROM huacales ORDER BY IdEntrada DESC")
    fun ObserveAll(): Flow<List<HuacalesEntity>>

    @Query("SELECT * FROM huacales WHERE IdEntrada = :id")
    suspend fun getById(id: Int?): HuacalesEntity?

    @Upsert
    suspend fun upsert(entity: HuacalesEntity)

    @Delete
    suspend fun delete(entity: HuacalesEntity)

    @Query("DELETE FROM huacales WHERE IdEntrada = :id")
    suspend fun deleteById(id: Int)

}
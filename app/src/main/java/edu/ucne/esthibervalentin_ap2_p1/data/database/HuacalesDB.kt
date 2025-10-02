package edu.ucne.esthibervalentin_ap2_p1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.esthibervalentin_ap2_p1.data.huacales.local.HuacalesDao
import edu.ucne.esthibervalentin_ap2_p1.data.huacales.local.HuacalesEntity

@Database(
    entities = [
        HuacalesEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class HuacalesDB : RoomDatabase() {
    abstract fun HuacalesDao() : HuacalesDao
}
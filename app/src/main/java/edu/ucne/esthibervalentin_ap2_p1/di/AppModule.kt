package edu.ucne.esthibervalentin_ap2_p1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.esthibervalentin_ap2_p1.data.database.HuacalesDB
import javax.inject.Singleton
import kotlin.jvm.java


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(appContext,
        HuacalesDB::class.java,
        "huacales_db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideHuacalesDao(huacalesDb: HuacalesDB) = huacalesDb.HuacalesDao()
}
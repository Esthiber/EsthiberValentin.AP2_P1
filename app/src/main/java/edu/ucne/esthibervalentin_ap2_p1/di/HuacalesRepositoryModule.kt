package edu.ucne.esthibervalentin_ap2_p1.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.esthibervalentin_ap2_p1.data.huacales.repository.HuacalesRepositoryImpl
import edu.ucne.esthibervalentin_ap2_p1.domain.repository.HuacalesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HuacalesRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindHuacalesRepository(
        huacalesRepositoryImpl: HuacalesRepositoryImpl
    ): HuacalesRepository
}
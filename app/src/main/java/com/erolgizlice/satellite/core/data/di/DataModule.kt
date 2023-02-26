package com.erolgizlice.satellite.core.data.di

import com.erolgizlice.satellite.core.data.repository.InterfaceSatelliteRepository
import com.erolgizlice.satellite.core.data.repository.SatelliteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsSatelliteRepository(
        satelliteRepository: SatelliteRepository,
    ): InterfaceSatelliteRepository
}
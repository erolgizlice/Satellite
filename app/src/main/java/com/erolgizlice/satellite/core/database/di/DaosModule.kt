package com.erolgizlice.satellite.core.database.di

import com.erolgizlice.satellite.core.database.SatelliteDatabase
import com.erolgizlice.satellite.core.database.dao.SatelliteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesNoteDao(
        database: SatelliteDatabase
    ): SatelliteDao = database.satelliteDao()
}
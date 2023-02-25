package com.erolgizlice.satellite.core.database.di

import android.content.Context
import androidx.room.Room
import com.erolgizlice.satellite.core.database.SatelliteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesNotesDatabase(
        @ApplicationContext context: Context
    ): SatelliteDatabase = Room.databaseBuilder(
        context,
        SatelliteDatabase::class.java,
        "satellite-database"
    ).build()
}
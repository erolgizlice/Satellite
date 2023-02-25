package com.erolgizlice.satellite.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.erolgizlice.satellite.core.database.dao.SatelliteDao
import com.erolgizlice.satellite.core.database.model.SatelliteDetailEntity

@Database(
    entities = [SatelliteDetailEntity::class],
    version = 1,
    exportSchema = true
)
abstract class SatelliteDatabase : RoomDatabase() {

    abstract fun satelliteDao(): SatelliteDao
}
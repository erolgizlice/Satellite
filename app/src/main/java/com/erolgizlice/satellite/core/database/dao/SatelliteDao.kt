package com.erolgizlice.satellite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.erolgizlice.satellite.core.database.model.SatelliteDetailEntity

@Dao
interface SatelliteDao {

    @Query("SELECT * FROM satellites WHERE id = :satelliteId")
    fun getSatelliteEntity(satelliteId: Int): SatelliteDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSatelliteDetailEntity(satellite: SatelliteDetailEntity)
}

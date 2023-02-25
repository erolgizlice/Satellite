package com.erolgizlice.satellite.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erolgizlice.satellite.core.model.data.SatelliteDetail

@Entity(tableName = "satellites")
data class SatelliteDetailEntity(
    @PrimaryKey
    val id: Int,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int
)

fun SatelliteDetailEntity.asExternalModel() = SatelliteDetail(
    id = id,
    costPerLaunch = costPerLaunch,
    firstFlight = firstFlight,
    height = height,
    mass = mass
)
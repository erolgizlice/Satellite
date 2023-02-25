package com.erolgizlice.satellite.core.model.data

import com.erolgizlice.satellite.core.database.model.SatelliteDetailEntity

data class SatelliteDetail(
    val id: Int,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int,
    val position: String
)

fun SatelliteDetail.asEntityModel() = SatelliteDetailEntity(
    id = id,
    costPerLaunch = costPerLaunch,
    firstFlight = firstFlight,
    height = height,
    mass = mass
)
package com.erolgizlice.satellite.core.model.data

data class SatelliteDetail(
    val id: Int,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int
)
package com.erolgizlice.satellite.feature.detail.data

import com.erolgizlice.satellite.core.model.data.SatelliteDetail

val satelliteDetailTestData: SatelliteDetail =
    SatelliteDetail(
        id = 1,
        costPerLaunch = 2,
        firstFlight = "2023",
        height = 3,
        mass = 4,
        position = "0.1, 0.2"
    )
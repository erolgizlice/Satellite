package com.erolgizlice.satellite.feature.home.data

import com.erolgizlice.satellite.core.model.data.Satellite

val satelliteListTestData: List<Satellite> = listOf(
    Satellite(
        id = 1,
        active = true,
        name = "test 1"
    ),
    Satellite(
        id = 2,
        active = false,
        name = "test 2"
    )
)
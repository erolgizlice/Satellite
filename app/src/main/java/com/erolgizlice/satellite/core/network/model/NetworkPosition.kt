package com.erolgizlice.satellite.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPosition(
    val posX: Double,
    val posY: Double
)
package com.erolgizlice.satellite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSatelliteDetail(
    val id: Int,
    @SerialName("cost_per_launch") val costPerLaunch: Int,
    @SerialName("first_flight") val firstFlight: String,
    val height: Int,
    val mass: Int
)
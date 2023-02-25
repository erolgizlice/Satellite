package com.erolgizlice.satellite.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkSatellite(
    val id: Int,
    val active: Boolean,
    val name: String
)

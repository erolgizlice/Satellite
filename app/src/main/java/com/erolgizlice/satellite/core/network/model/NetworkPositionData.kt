package com.erolgizlice.satellite.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPositionData(
    val id: String,
    var positions: List<NetworkPosition>
)

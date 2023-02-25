package com.erolgizlice.satellite.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPositionContainer(
    val list: List<NetworkPositionData>
)

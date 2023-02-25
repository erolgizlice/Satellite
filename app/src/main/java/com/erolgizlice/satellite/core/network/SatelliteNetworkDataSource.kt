package com.erolgizlice.satellite.core.network

import com.erolgizlice.satellite.core.network.model.NetworkPositionContainer
import com.erolgizlice.satellite.core.network.model.NetworkSatellite
import com.erolgizlice.satellite.core.network.model.NetworkSatelliteDetail

interface SatelliteNetworkDataSource {

    suspend fun getSatelliteList(): List<NetworkSatellite>

    suspend fun getSatelliteDetailList(): List<NetworkSatelliteDetail>

    suspend fun getSatellitePosition(): NetworkPositionContainer
}

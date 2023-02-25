package com.erolgizlice.satellite.core.data.repository

import com.erolgizlice.satellite.core.model.data.Satellite
import com.erolgizlice.satellite.core.model.data.SatelliteDetail
import kotlinx.coroutines.flow.Flow

interface InterfaceSatelliteRepository {

    fun getSatelliteList(): Flow<List<Satellite>>

    fun getSatelliteDetail(satelliteId: Int): Flow<SatelliteDetail>

    fun getSatellitePosition(satelliteId: Int): Flow<String>
}

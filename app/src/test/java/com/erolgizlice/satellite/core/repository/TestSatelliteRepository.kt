package com.erolgizlice.satellite.core.repository

import com.erolgizlice.satellite.core.data.repository.InterfaceSatelliteRepository
import com.erolgizlice.satellite.core.model.data.Satellite
import com.erolgizlice.satellite.core.model.data.SatelliteDetail
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestSatelliteRepository: InterfaceSatelliteRepository {

    private val satelliteListFlow: MutableSharedFlow<List<Satellite>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val satelliteDetailFlow: MutableSharedFlow<SatelliteDetail> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val satellitePositionFlow: MutableSharedFlow<String> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getSatelliteList(): Flow<List<Satellite>> = satelliteListFlow

    override fun getSatelliteDetail(satelliteId: Int): Flow<SatelliteDetail> = satelliteDetailFlow

    override fun getSatellitePosition(satelliteId: Int): Flow<String> = satellitePositionFlow

    fun sendSatellites(satelliteList: List<Satellite>) {
        satelliteListFlow.tryEmit(satelliteList)
    }

    fun sendSatelliteDetail(satelliteDetail: SatelliteDetail) {
        satelliteDetailFlow.tryEmit(satelliteDetail)
    }

    fun sendSatellitePosition(satellitePosition: String) {
        satellitePositionFlow.tryEmit(satellitePosition)
    }
}

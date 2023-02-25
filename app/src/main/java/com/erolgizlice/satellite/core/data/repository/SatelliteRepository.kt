package com.erolgizlice.satellite.core.data.repository

import com.erolgizlice.satellite.core.model.data.Satellite
import com.erolgizlice.satellite.core.model.data.SatelliteDetail
import com.erolgizlice.satellite.core.network.Dispatcher
import com.erolgizlice.satellite.core.network.NetworkDataSource
import com.erolgizlice.satellite.core.network.SatelliteDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SatelliteRepository @Inject constructor(
    @Dispatcher(SatelliteDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val datasource: NetworkDataSource
) : InterfaceSatelliteRepository {

    override fun getSatelliteList(): Flow<List<Satellite>> = flow {
        emit(
            datasource.getSatelliteList().map {
                Satellite(
                    id = it.id,
                    active = it.active,
                    name = it.name
                )
            }
        )
    }.flowOn(ioDispatcher)

    override fun getSatelliteDetailList(): Flow<List<SatelliteDetail>> = flow {
        emit(
            datasource.getSatelliteDetailList().map {
                SatelliteDetail(
                    id = it.id,
                    costPerLaunch = it.costPerLaunch,
                    firstFlight = it.firstFlight,
                    height = it.height,
                    mass = it.mass
                )
            }
        )
    }.flowOn(ioDispatcher)
}
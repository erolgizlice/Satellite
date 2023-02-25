package com.erolgizlice.satellite.core.data.repository

import com.erolgizlice.satellite.core.database.dao.SatelliteDao
import com.erolgizlice.satellite.core.database.model.asExternalModel
import com.erolgizlice.satellite.core.model.data.Satellite
import com.erolgizlice.satellite.core.model.data.SatelliteDetail
import com.erolgizlice.satellite.core.model.data.asEntityModel
import com.erolgizlice.satellite.core.network.Dispatcher
import com.erolgizlice.satellite.core.network.NetworkDataSource
import com.erolgizlice.satellite.core.network.SatelliteDispatchers
import com.erolgizlice.satellite.core.network.model.NetworkPosition
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SatelliteRepository @Inject constructor(
    @Dispatcher(SatelliteDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val datasource: NetworkDataSource,
    private val satelliteDao: SatelliteDao
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

    override fun getSatelliteDetail(satelliteId: Int): Flow<SatelliteDetail> = flow {
        emit(
            satelliteDao.getSatelliteEntity(satelliteId)?.asExternalModel()
                ?: getSatelliteDetailJson(satelliteId)
        )
    }.flowOn(ioDispatcher)

    private suspend fun getSatelliteDetailJson(satelliteId: Int) =
        datasource.getSatelliteDetailList().filter { it.id == satelliteId }.map {
            SatelliteDetail(
                id = it.id,
                costPerLaunch = it.costPerLaunch,
                firstFlight = it.firstFlight,
                height = it.height,
                mass = it.mass,
                position = ""
            )
        }.first()
            .also {
                satelliteDao.insertSatelliteDetailEntity(it.asEntityModel())
            }

    override fun getSatellitePosition(satelliteId: Int): Flow<String> = flow {
        var index = 0
        var positions: List<NetworkPosition>
        while (true) {
            emit(
                datasource.getSatellitePosition().list
                    .first { it.id == satelliteId.toString() }
                    .apply { positions = this.positions }
                    .positions[index]
                    .let { "${it.posX}, ${it.posY}" }
            )
            index = (index + 1) % positions.size
            delay(3000)
        }
    }.flowOn(ioDispatcher)
}
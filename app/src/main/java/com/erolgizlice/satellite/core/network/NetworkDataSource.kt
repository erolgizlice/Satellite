package com.erolgizlice.satellite.core.network

import JvmUnitTestAssetManager
import com.erolgizlice.satellite.core.network.model.NetworkPositionContainer
import com.erolgizlice.satellite.core.network.model.NetworkSatellite
import com.erolgizlice.satellite.core.network.model.NetworkSatelliteDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    @Dispatcher(SatelliteDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: AssetManagerInterface = JvmUnitTestAssetManager,
) : SatelliteNetworkDataSource {

    companion object {
        private const val SATELLITE_LIST_ASSET = "satellite-list.json"
        private const val SATELLITE_DETAIL_ASSET = "satellite-detail.json"
        private const val POSITIONS_ASSET = "positions.json"
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getSatelliteList(): List<NetworkSatellite> =
        withContext(ioDispatcher) {
            assets.open(SATELLITE_LIST_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getSatelliteDetailList(): List<NetworkSatelliteDetail> =
        withContext(ioDispatcher) {
            assets.open(SATELLITE_DETAIL_ASSET).use(networkJson::decodeFromStream)
        }

    override suspend fun getSatellitePosition(): NetworkPositionContainer =
        withContext(ioDispatcher) {
            assets.open(POSITIONS_ASSET).use(networkJson::decodeFromStream)
        }
}
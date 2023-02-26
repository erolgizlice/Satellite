package com.erolgizlice.satellite.core.network

import JvmUnitTestAssetManager
import com.erolgizlice.satellite.core.network.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class NetworkDataSourceTest {

    private lateinit var subject: NetworkDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        subject = NetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestAssetManager
        )
    }

    @Test
    fun testDeserializationOfSatelliteList() = runTest(testDispatcher) {
        assertEquals(
            NetworkSatellite(
                id = 1,
                active = false,
                name = "Starship-1"
            ),
            subject.getSatelliteList().first()
        )
    }

    @Test
    fun testDeserializationOfSatelliteDetail() = runTest(testDispatcher) {
        assertEquals(
            NetworkSatelliteDetail(
                id = 1,
                costPerLaunch = 7200000,
                firstFlight = "2021-12-01",
                height = 118,
                mass = 1167000
            ),
            subject.getSatelliteDetailList().first()
        )
    }

    @Test
    fun testDeserializationOfSatellitePositions() = runTest(testDispatcher) {
        assertEquals(
            NetworkPosition(
                posX = 0.864328541,
                posY = 0.646450811
            ),
            subject.getSatellitePosition().list.first().positions.first()
        )
    }
}
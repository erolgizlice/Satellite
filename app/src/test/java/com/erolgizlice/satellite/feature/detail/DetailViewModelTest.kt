package com.erolgizlice.satellite.feature.detail

import androidx.lifecycle.SavedStateHandle
import com.erolgizlice.satellite.core.domain.GetSatelliteDetailUseCase
import com.erolgizlice.satellite.core.model.data.SatelliteDetail
import com.erolgizlice.satellite.core.repository.TestSatelliteRepository
import com.erolgizlice.satellite.core.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class DetailViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val satelliteRepository = TestSatelliteRepository()
    private val getSatelliteDetailUseCase = GetSatelliteDetailUseCase(
        satelliteRepository = satelliteRepository
    )
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        viewModel = DetailViewModel(
            getSatelliteDetailUseCase = getSatelliteDetailUseCase,
            savedStateHandle = SavedStateHandle(
                mapOf(
                    "satelliteId" to satelliteDetailTestData.id,
                    "satelliteName" to "name"
                )
            )
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun uiState_whenInitialized_thenShowLoading() = runTest {
        assertEquals(SatelliteDetailUiState.Loading, viewModel.satelliteDetailUiState.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun uiState_whenSatelliteDetailSuccess_thenShowSuccess() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.satelliteDetailUiState.collect()
        }

        satelliteRepository.sendSatelliteDetail(satelliteDetailTestData)
        satelliteRepository.sendSatellitePosition(satelliteDetailTestData.position)
        val uiState = viewModel.satelliteDetailUiState.value

        assertIs<SatelliteDetailUiState.Success>(uiState)

        collectJob.cancel()
    }
}

val satelliteDetailTestData: SatelliteDetail =
    SatelliteDetail(
        id = 1,
        costPerLaunch = 2,
        firstFlight = "2023",
        height = 3,
        mass = 4,
        position = "0.1, 0.2"
    )
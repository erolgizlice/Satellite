package com.erolgizlice.satellite.feature.home

import com.erolgizlice.satellite.core.domain.GetSatelliteListUseCase
import com.erolgizlice.satellite.core.model.data.Satellite
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

class HomeViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val satelliteRepository = TestSatelliteRepository()
    private val getSatelliteListUseCase = GetSatelliteListUseCase(
        satelliteRepository = satelliteRepository
    )
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(
            getSatelliteListUseCase = getSatelliteListUseCase
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun uiState_whenInitialized_thenShowLoading() = runTest {
        assertEquals(HomeUiState.Loading, viewModel.uiState.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun uiState_whenSatelliteListSuccess_thenShowSuccess() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect()
        }

        satelliteRepository.sendSatellites(satelliteListTestData)
        val uiState = viewModel.uiState.value

        assertIs<HomeUiState.Success>(uiState)

        collectJob.cancel()
    }
}

val satelliteListTestData: List<Satellite> = listOf(
    Satellite(
        id = 1,
        active = true,
        name = "test 1"
    ),
    Satellite(
        id = 2,
        active = false,
        name = "test 2"
    )
)
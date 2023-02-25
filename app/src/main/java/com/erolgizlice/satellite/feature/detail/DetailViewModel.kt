package com.erolgizlice.satellite.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erolgizlice.satellite.core.domain.GetSatelliteDetailUseCase
import com.erolgizlice.satellite.core.model.data.SatelliteDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    getSatelliteDetailUseCase: GetSatelliteDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val satelliteId = savedStateHandle.get<Int>("satelliteId")!!

    val satelliteDetailUiState: StateFlow<SatelliteDetailUiState> =
            getSatelliteDetailUseCase(satelliteId)
                .map(SatelliteDetailUiState::Success)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = SatelliteDetailUiState.Loading,
                )
}

sealed interface SatelliteDetailUiState {
    data class Success(val satelliteDetail: SatelliteDetail) : SatelliteDetailUiState
    object Error : SatelliteDetailUiState
    object Loading : SatelliteDetailUiState

}

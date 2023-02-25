package com.erolgizlice.satellite.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erolgizlice.satellite.core.domain.GetSatelliteListUseCase
import com.erolgizlice.satellite.core.model.data.Satellite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getSatelliteListUseCase: GetSatelliteListUseCase
): ViewModel() {

    val uiState: StateFlow<HomeUiState> =
        getSatelliteListUseCase()
            .map(HomeUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HomeUiState.Loading,
            )
}

sealed interface HomeUiState {
    object Loading: HomeUiState

    data class Success(
        val satelliteList: List<Satellite>
    ) : HomeUiState

    object Error : HomeUiState
}

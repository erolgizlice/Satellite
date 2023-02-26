package com.erolgizlice.satellite.feature.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DetailRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.satelliteDetailUiState.collectAsStateWithLifecycle()
    val name = viewModel.satelliteName

    DetailScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        modifier = modifier,
        name = name
    )
}

@Composable
fun DetailScreen(
    uiState: SatelliteDetailUiState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    name: String = ""
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            SatelliteDetailUiState.Loading -> CircularProgressIndicator(
                modifier = Modifier.semantics { contentDescription = "Loading detail" }
            )
            is SatelliteDetailUiState.Success ->
                DetailContent(
                    satelliteDetail = uiState.satelliteDetail,
                    onBackClick = onBackClick,
                    modifier = modifier,
                    name = name
                )
            SatelliteDetailUiState.Error -> Text(text = "Couldn't fetch data")
        }
    }
}

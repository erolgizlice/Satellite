package com.erolgizlice.satellite.feature.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DetailRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.satelliteDetailUiState.collectAsStateWithLifecycle()

    Text(text = uiState.toString())
}
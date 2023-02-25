package com.erolgizlice.satellite.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.erolgizlice.satellite.navigation.SatelliteNavHost

@Composable
fun SatelliteApp(
    appState: SatelliteAppState = rememberSatelliteAppState()
) {
    Scaffold { paddingValues ->
        SatelliteNavHost(
            modifier = Modifier.padding(paddingValues),
            navController = appState.navController
        )
    }
}
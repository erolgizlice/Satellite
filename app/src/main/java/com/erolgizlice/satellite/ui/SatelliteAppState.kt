package com.erolgizlice.satellite.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberSatelliteAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): SatelliteAppState {
    return remember(navController, coroutineScope) {
        SatelliteAppState(navController, coroutineScope)
    }
}

class SatelliteAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {

}

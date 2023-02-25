package com.erolgizlice.satellite.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.erolgizlice.satellite.feature.detail.navigation.detailScreen
import com.erolgizlice.satellite.feature.detail.navigation.navigateToDetail
import com.erolgizlice.satellite.feature.home.navigation.homeGraph
import com.erolgizlice.satellite.feature.home.navigation.homeGraphRoutePattern

@Composable
fun SatelliteNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = homeGraphRoutePattern
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeGraph(
            onSatelliteClick = { satelliteId, satelliteName ->
                navController.navigateToDetail(
                    satelliteId = satelliteId,
                    satelliteName = satelliteName
                )
            },
            nestedGraphs = {
                detailScreen(
                    onBackClick = navController::popBackStack
                )
            }
        )
    }
}
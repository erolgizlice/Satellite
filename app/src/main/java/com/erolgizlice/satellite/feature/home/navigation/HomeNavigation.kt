package com.erolgizlice.satellite.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.erolgizlice.satellite.feature.home.HomeRoute

const val homeGraphRoutePattern = "home_graph"
const val homeRoute = "home_route"

fun NavGraphBuilder.homeGraph(
    onSatelliteClick: (Int, String) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = homeGraphRoutePattern,
        startDestination = homeRoute
    ) {
        composable(route = homeRoute) {
            HomeRoute(onSatelliteClick)
        }
        nestedGraphs()
    }
}
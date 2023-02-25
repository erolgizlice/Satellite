package com.erolgizlice.satellite.feature.detail.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.erolgizlice.satellite.feature.detail.DetailRoute

const val detailRoute = "detail_route"

fun NavController.navigateToDetail(
    navOptions: NavOptions? = null,
    satelliteId: Int = -1,
    satelliteName: String = ""
) {
    this.navigate(
        "$detailRoute?satelliteId=$satelliteId&satelliteName=$satelliteName",
        navOptions
    )
}

fun NavGraphBuilder.detailScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "$detailRoute?satelliteId={satelliteId}&satelliteName={satelliteName}",
        arguments = listOf(
            navArgument(
                name = "satelliteId"
            ) {
                type = NavType.IntType
                defaultValue = -1
            },
            navArgument(
                name = "satelliteName"
            ) {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) {
        DetailRoute(
            onBackClick = onBackClick
        )
    }
}
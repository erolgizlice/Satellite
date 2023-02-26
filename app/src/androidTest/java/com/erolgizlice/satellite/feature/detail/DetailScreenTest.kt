package com.erolgizlice.satellite.feature.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.erolgizlice.satellite.feature.detail.data.satelliteDetailTestData
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var detailLoading: String

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            detailLoading = "Loading detail"
        }
    }

    @Test
    fun whenScreenIsLoading_showLoading() {
        composeTestRule.setContent {
            DetailScreen(
                uiState = SatelliteDetailUiState.Loading,
                onBackClick = { }
            )
        }

        composeTestRule
            .onNodeWithContentDescription(detailLoading)
            .assertExists()
    }

    @Test
    fun whenDetailIsSuccess_isShown() {
        val testSatelliteDetail = satelliteDetailTestData
        composeTestRule.setContent {
            DetailScreen(
                uiState = SatelliteDetailUiState.Success(testSatelliteDetail),
                onBackClick = { }
            )
        }

        composeTestRule
            .onNodeWithText(testSatelliteDetail.firstFlight)
            .assertExists()
    }
}
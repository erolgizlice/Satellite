package com.erolgizlice.satellite.feature.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.erolgizlice.satellite.feature.home.data.satelliteListTestData
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var homeLoading: String

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            homeLoading = "Loading home"
        }
    }

    @Test
    fun whenScreenIsLoading_showLoading() {
        composeTestRule.setContent {
            HomeScreen(
                uiState = HomeUiState.Loading,
                onSatelliteClick = { int, str -> }
            )
        }

        composeTestRule
            .onNodeWithContentDescription(homeLoading)
            .assertExists()
    }

    @Test
    fun whenHomeIsSuccess_isShown() {
        val testSatelliteList = satelliteListTestData
        composeTestRule.setContent {
            HomeScreen(
                uiState = HomeUiState.Success(testSatelliteList),
                onSatelliteClick = { int, str -> }
            )
        }

        composeTestRule
            .onNodeWithText(testSatelliteList.first().name)
            .assertExists()
    }
}
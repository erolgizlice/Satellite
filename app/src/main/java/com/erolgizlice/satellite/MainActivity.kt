package com.erolgizlice.satellite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.erolgizlice.satellite.ui.SatelliteApp
import com.erolgizlice.satellite.ui.theme.SatelliteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SatelliteTheme {
                SatelliteApp()
            }
        }
    }
}
package com.erolgizlice.satellite.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.erolgizlice.satellite.core.model.data.Satellite


@Composable
fun HomeContent(
    satelliteList: List<Satellite>,
    onSatelliteClick: (Int) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        satelliteList.forEachIndexed { index, satellite ->
            item(key = satellite.id) {
                SatelliteItem(
                    name = satellite.name,
                    isActive = satellite.active,
                    onSatelliteClick = { onSatelliteClick(satellite.id) }
                )
            }

            if (index != satelliteList.size - 1) {
                item {
                    Divider()
                }
            }
        }
    }
}

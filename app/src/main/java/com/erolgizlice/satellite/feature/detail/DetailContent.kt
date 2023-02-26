package com.erolgizlice.satellite.feature.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.erolgizlice.satellite.core.model.data.SatelliteDetail


@Composable
fun DetailContent(
    satelliteDetail: SatelliteDetail,
    onBackClick: () -> Unit,
    modifier: Modifier,
    name: String
) {
    Box(modifier = modifier.fillMaxSize()) {
        IconButton(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = onBackClick
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "back"
            )
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = satelliteDetail.firstFlight,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Height/Mass: ${satelliteDetail.height}/${satelliteDetail.mass}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Cost: ${satelliteDetail.costPerLaunch}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Last Position: (${satelliteDetail.position})",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
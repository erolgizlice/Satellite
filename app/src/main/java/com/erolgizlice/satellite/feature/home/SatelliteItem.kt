package com.erolgizlice.satellite.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SatelliteItem(
    name: String,
    isActive: Boolean,
    onSatelliteClick: () -> Unit
) {
    val textColor = if (isActive) Color.Black else Color.Gray

    Row(
        modifier = Modifier
            .clickable(enabled = isActive) {
                onSatelliteClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    if (isActive) Color.Green else Color.Red,
                    shape = CircleShape
                )
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                color = textColor
            )
            Text(
                text = if (isActive) "Active" else "Passive",
                style = MaterialTheme.typography.bodyMedium,
                color = textColor
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
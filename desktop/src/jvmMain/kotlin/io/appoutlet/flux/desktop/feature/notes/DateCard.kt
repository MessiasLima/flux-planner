package io.appoutlet.flux.desktop.feature.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import io.appoutlet.flux.common.core.ui.Spacing
import io.appoutlet.flux.common.core.ui.shapeMedium

@Composable
fun DateCard(modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = shapeMedium) {
        Column(
            modifier = Modifier.padding(vertical = Spacing.Small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("11:04", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Text("Monday , April 11", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

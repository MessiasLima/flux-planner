package io.appoutlet.flux.desktop.feature.notes

import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.appoutlet.flux.common.core.ui.shapeMedium

@Composable
fun CalendarCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = shapeMedium
    ) {
        Text("Calendar view")
    }
}

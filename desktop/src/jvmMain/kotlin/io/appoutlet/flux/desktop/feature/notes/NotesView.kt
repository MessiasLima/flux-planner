package io.appoutlet.flux.desktop.feature.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.appoutlet.flux.common.core.ui.Spacing
import io.appoutlet.flux.common.core.ui.shapeMedium

@Composable
fun NotesView() {
    Row {
        Column(modifier = Modifier.weight(1f)) {
            Text("Content")
        }

        Card(
            modifier = Modifier.width(250.dp).fillMaxHeight(),
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            shape = shapeMedium.copy(topEnd = CornerSize(0), bottomEnd = CornerSize(0))
        ) {
            Column(modifier = Modifier.padding(all = Spacing.Small)) {
                DateCard(modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(Spacing.Small))

                CalendarCard(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

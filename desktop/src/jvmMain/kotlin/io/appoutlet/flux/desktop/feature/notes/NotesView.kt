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
import androidx.compose.ui.Alignment
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
            modifier = Modifier.width(428.dp).fillMaxHeight(),
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            shape = shapeMedium.copy(topEnd = CornerSize(0), bottomEnd = CornerSize(0))
        ) {
            Column(modifier = Modifier.padding(horizontal = Spacing.Medium).padding(top = 49.dp)) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = shapeMedium
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = Spacing.Large),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("11:04", style = MaterialTheme.typography.bodyLarge)
                        Text("Monday , April 11", style = MaterialTheme.typography.bodyMedium)
                    }
                }

                Spacer(modifier = Modifier.height(65.dp))

                Card(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    shape = shapeMedium
                ) {
                    Text("Calendario", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

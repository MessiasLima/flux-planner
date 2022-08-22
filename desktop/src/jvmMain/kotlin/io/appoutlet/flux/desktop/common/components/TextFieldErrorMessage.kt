package io.appoutlet.flux.desktop.common.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@ExperimentalComposeUiApi
@Composable
fun TextFieldErrorMessage(show: Boolean, message: String) {
    val alpha by animateFloatAsState(if (show) 1f else 0f)

    Text(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 16.dp)
            .alpha(alpha)
            .semantics {
                if (!show) set(SemanticsProperties.InvisibleToUser, Unit)
            },
        text = message,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
    )
}

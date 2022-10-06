package io.appoutlet.flux.desktop.common.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@ExperimentalComposeUiApi
@Composable
fun FluxProgressBar(modifier: Modifier = Modifier, isLoading: Boolean = true) {
    val alpha by animateFloatAsState(if (isLoading) 1f else 0f)
    LinearProgressIndicator(
        modifier = modifier
            .alpha(alpha)
            .semantics {
                contentDescription = "Progress bar"
                if (!isLoading) {
                    set(SemanticsProperties.InvisibleToUser, Unit)
                }
            },
    )
}

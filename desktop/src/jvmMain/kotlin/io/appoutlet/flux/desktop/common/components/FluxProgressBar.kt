package io.appoutlet.flux.desktop.common.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

@Composable
fun FluxProgressBar(modifier: Modifier = Modifier, isLoading: Boolean = true) {
    val alpha by animateFloatAsState(if (isLoading) 1f else 0f)
    LinearProgressIndicator(modifier = modifier.fillMaxWidth().alpha(alpha))
}

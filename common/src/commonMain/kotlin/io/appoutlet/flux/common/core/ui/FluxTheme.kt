package io.appoutlet.flux.common.core.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun FluxTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) darkColors else lightColors

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        content = content,
    )
}

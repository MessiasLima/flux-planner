package io.appoutlet.flux.common.core.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun FluxTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) darkColors else lightColors

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        content = content,
    )
}

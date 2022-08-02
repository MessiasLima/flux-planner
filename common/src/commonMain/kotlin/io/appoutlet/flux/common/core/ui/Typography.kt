package io.appoutlet.flux.common.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Display Large
val displayLarge = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 57.sp,
    lineHeight = 64.sp,
    letterSpacing = 0.25.sp
)

// Headline large
val headlineLarge = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 32.sp,
    lineHeight = 40.sp,
)

// Body large
val bodyLarge = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
)

val typography: Typography
    @Composable get() = MaterialTheme.typography.copy(
        displayLarge = displayLarge,
        headlineLarge = headlineLarge,
        bodyLarge = bodyLarge
    )

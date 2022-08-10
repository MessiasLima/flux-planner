@file:Suppress("MagicNumber")

package io.appoutlet.flux.common.core.ui

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val purple40 = Color(0xff58598F)
val purple95 = Color(0xffF2EFFF)
val purple99 = Color(0xffFFFBFF)

val grey10 = Color(0xFF1C1B1F)
val error40 = Color(0xffba1a1a)

val lightColors = lightColorScheme(
    primary = purple40,
    surface = purple99,
    surfaceVariant = purple95,
    onSurface = grey10,
    error = error40
)

val darkColors = darkColorScheme()

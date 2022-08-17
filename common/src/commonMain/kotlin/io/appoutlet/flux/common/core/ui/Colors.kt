@file:Suppress("MagicNumber")

package io.appoutlet.flux.common.core.ui

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Primary
val primary10 = Color(0xFF0B0665)
val primary20 = Color(0xFF242478)
val primary30 = Color(0xFF3B3D90)
val primary50 = Color(0xFF6C6FC4)
val primary80 = Color(0xFFC1C1FF)
val primary90 = Color(0xFFE1DFFF)
val primary100 = Color(0xFFFFFFFF)

// Secondary
val secondary10 = Color(0xFF270057)
val secondary20 = Color(0xFF3D1C6F)
val secondary30 = Color(0xFF543587)
val secondary80 = Color(0xFFA180D8)
val secondary90 = Color(0xFFECDCFF)
val secondary100 = Color(0xFFFFFFFF)

// Tertiary
val tertiary10 = Color(0xFF3B002D)
val tertiary20 = Color(0xFF591146)
val tertiary30 = Color(0xFF752A5E)
val tertiary70 = Color(0xFFEA8DC7)
val tertiary80 = Color(0xFFFFAEDE)
val tertiary90 = Color(0xFFFFD8EC)
val tertiary100 = Color(0xFFFFFFFF)

// Error
val error10 = Color(0xFF410002)
val error20 = Color(0xFF690005)
val error30 = Color(0xFF93000A)
val error40 = Color(0xFFBA1A1A)
val error80 = Color(0xFFFFB4AB)
val error90 = Color(0xFFFFDAD6)
val error100 = Color(0xFFFFFFFF)

// Neutral
val neutral10 = Color(0xFF0B0664)
val neutral80 = Color(0xFFE1DFFF)
val neutral90 = Color(0xFFE1DFFF)
val neutral99 = Color(0xFFFFFBFF)

// Neutral Variant
val neutralVariant30 = Color(0xFF47464F)
val neutralVariant50 = Color(0xFF777680)
val neutralVariant60 = Color(0xFF918F9A)
val neutralVariant80 = Color(0xFFC8C5D0)
val neutralVariant90 = Color(0xFFE4E1EC)

val lightColors = lightColorScheme(
    primary = primary50,
    onPrimary = primary100,
    primaryContainer = primary90,
    onPrimaryContainer = primary10,

    secondary = secondary80,
    onSecondary = secondary100,
    secondaryContainer = secondary90,
    onSecondaryContainer = secondary10,

    tertiary = tertiary70,
    onTertiary = tertiary100,
    tertiaryContainer = tertiary90,
    onTertiaryContainer = tertiary10,

    error = error40,
    onError = error100,
    errorContainer = error90,
    onErrorContainer = error10,

    background = neutral99,
    onBackground = neutral10,
    surface = neutral99,
    onSurface = neutral10,

    surfaceVariant = neutralVariant90,
    onSurfaceVariant = neutralVariant30,
    outline = neutralVariant50
)

val darkColors = darkColorScheme(
    primary = primary80,
    onPrimary = primary20,
    primaryContainer = primary30,
    onPrimaryContainer = primary90,

    secondary = secondary80,
    onSecondary = secondary20,
    secondaryContainer = secondary30,
    onSecondaryContainer = secondary90,

    tertiary = tertiary80,
    onTertiary = tertiary20,
    tertiaryContainer = tertiary30,
    onTertiaryContainer = tertiary90,

    error = error80,
    onError = error20,
    errorContainer = error30,
    onErrorContainer = error90,

    background = neutral10,
    onBackground = neutral90,
    surface = neutral10,
    onSurface = neutral80,

    surfaceVariant = neutralVariant30,
    onSurfaceVariant = neutralVariant80,
    outline = neutralVariant60
)

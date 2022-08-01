package io.appoutlet.flux.common.core.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

private val shapeSmall = RoundedCornerShape(4.dp)
private val shapeMedium = RoundedCornerShape(14.dp)
private val shapeLarge = RoundedCornerShape(18.dp)

val shapes = Shapes(
    small = shapeSmall,
    medium = shapeMedium,
    large = shapeLarge
)

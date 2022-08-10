package io.appoutlet.flux.desktop.common

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.useResource

object FluxIcons : FluxSvgLoader() {
    override val baseFolder: String = "image/icon/"
}

val FluxIcons.Cancel: Painter
    @Composable
    get() = loadSvgResource("ic_cancel.svg")

val FluxIcons.Visibility: Painter
    @Composable
    get() = loadSvgResource("ic_visibility.svg")

val FluxIcons.VisibilityOff: Painter
    @Composable
    get() = loadSvgResource("ic_visibility_off.svg")

@Suppress("UNUSED")
val Icons.Flux: FluxIcons
    get() = FluxIcons

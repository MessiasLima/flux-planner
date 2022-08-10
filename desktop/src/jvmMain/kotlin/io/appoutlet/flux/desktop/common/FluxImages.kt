package io.appoutlet.flux.desktop.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

object FluxImages : FluxSvgLoader() {
    override val baseFolder = "image/img/"
    val Logo: Painter
        @Composable
        get() = loadSvgResource("img_logo.svg")
}

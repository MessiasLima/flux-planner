package io.appoutlet.flux.desktop.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.useResource

abstract class FluxSvgLoader {
    abstract val baseFolder: String

    @Composable
    fun loadSvgResource(path: String): Painter {
        return useResource(baseFolder + path) {
            loadSvgPainter(
                inputStream = it,
                density = LocalDensity.current,
            )
        }
    }
}

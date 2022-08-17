package io.appoutlet.flux.desktop.common

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

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

val FluxIcons.AccountCircle: Painter
    @Composable
    get() = loadSvgResource("ic_account_circle.svg")

val FluxIcons.Mail: Painter
    @Composable
    get() = loadSvgResource("ic_mail.svg")

@Suppress("UNUSED")
val Icons.Flux: FluxIcons
    get() = FluxIcons

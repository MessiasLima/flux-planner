package io.appoutlet.flux.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.appoutlet.flux.common.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

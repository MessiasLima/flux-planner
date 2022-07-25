package io.appoutlet.flux.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.appoutlet.flux.common.feature.login.LoginView
import io.appoutlet.flux.common.feature.login.LoginViewModel

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Flux planner",
    ) {
        LoginView(LoginViewModel())
    }
}

package io.appoutlet.flux.desktop

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.appoutlet.flux.common.feature.login.LoginView
import io.appoutlet.flux.common.feature.login.LoginViewModel
import io.appoutlet.karavel.Karavel
import io.appoutlet.karavel.Page

fun main() = application {
    val karavel = Karavel(LoginPage())
    Window(
        onCloseRequest = ::exitApplication,
        title = "Flux planner",
    ) {
       karavel.currentPage().content()
    }
}

class LoginPage : Page() {
    @Composable
    override fun content() {
        LoginView(LoginViewModel())
    }
}

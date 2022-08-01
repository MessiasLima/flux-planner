package io.appoutlet.flux.desktop.feature.login

import androidx.compose.runtime.Composable
import io.appoutlet.karavel.Page

class LoginPage : Page() {
    @Composable
    override fun content() {
        LoginView()
    }
}

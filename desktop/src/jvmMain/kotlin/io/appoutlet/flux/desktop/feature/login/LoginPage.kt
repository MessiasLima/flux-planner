package io.appoutlet.flux.desktop.feature.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import io.appoutlet.karavel.Page

@ExperimentalFoundationApi
class LoginPage : Page() {
    @Composable
    override fun content() {
        LoginView()
    }
}

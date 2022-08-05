package io.appoutlet.flux.desktop.feature.crateaccount

import androidx.compose.runtime.Composable
import io.appoutlet.flux.desktop.feature.login.SignUpView
import io.appoutlet.karavel.Page

class SignUpPage : Page() {
    @Composable
    override fun content() {
        SignUpView(karavel)
    }
}

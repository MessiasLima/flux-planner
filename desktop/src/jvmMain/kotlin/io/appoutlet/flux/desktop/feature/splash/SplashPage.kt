package io.appoutlet.flux.desktop.feature.splash

import androidx.compose.runtime.Composable
import io.appoutlet.flux.desktop.feature.login.LoginPage
import io.appoutlet.karavel.Page

class SplashPage : Page() {
    @Composable
    override fun content() {
        SplashView {
            karavel?.navigate(LoginPage())
        }
    }
}

package io.appoutlet.flux.desktop.feature.splash

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import io.appoutlet.flux.desktop.feature.login.LoginPage
import io.appoutlet.karavel.Page

@ExperimentalFoundationApi
class SplashPage : Page() {
    @Composable
    override fun content() {
        SplashView {
            karavel?.navigate(LoginPage())
        }
    }
}

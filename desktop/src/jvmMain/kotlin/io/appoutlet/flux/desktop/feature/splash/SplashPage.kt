package io.appoutlet.flux.desktop.feature.splash

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import io.appoutlet.karavel.Page

class SplashPage : Page() {
    @Composable
    override fun content() {
        SplashView()
    }
}

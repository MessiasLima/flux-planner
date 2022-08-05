package io.appoutlet.flux.desktop.feature.signin

import androidx.compose.runtime.Composable
import io.appoutlet.karavel.Page

class SignInPage : Page() {
    @Composable
    override fun content() {
        SignInView(karavel)
    }
}

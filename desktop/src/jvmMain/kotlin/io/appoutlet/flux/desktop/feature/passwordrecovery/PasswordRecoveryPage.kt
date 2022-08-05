package io.appoutlet.flux.desktop.feature.passwordrecovery

import androidx.compose.runtime.Composable
import io.appoutlet.flux.desktop.feature.login.ForgotPasswordView
import io.appoutlet.karavel.Page

class PasswordRecoveryPage : Page() {
    @Composable
    override fun content() {
        ForgotPasswordView {
            karavel?.back()
        }
    }
}

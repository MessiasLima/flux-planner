package io.appoutlet.flux.desktop.feature.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import io.appoutlet.karavel.Page
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
class LoginPage : Page() {
    @Composable
    override fun content() {
        LoginView(karavel)
    }
}

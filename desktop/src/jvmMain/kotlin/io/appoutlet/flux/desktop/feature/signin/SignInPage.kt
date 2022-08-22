package io.appoutlet.flux.desktop.feature.signin

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import io.appoutlet.karavel.Karavel
import io.appoutlet.karavel.Page
import kotlinx.coroutines.FlowPreview

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@FlowPreview
class SignInPage(private val mainKaravel: Karavel?) : Page() {
    @Composable
    override fun content() {
        SignInView(karavel = karavel, mainKaravel = mainKaravel)
    }
}

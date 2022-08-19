package io.appoutlet.flux.desktop.feature.crateaccount

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import io.appoutlet.flux.desktop.di.koin
import io.appoutlet.flux.desktop.feature.splash.SplashPage
import io.appoutlet.karavel.Karavel
import io.appoutlet.karavel.Page
import kotlinx.coroutines.FlowPreview

@ExperimentalFoundationApi
@FlowPreview
class CreateAccountPage(
    private val mainKaravel: Karavel?
) : Page() {
    @Composable
    override fun content() {
        CreateAccountView(karavel = karavel, viewModel = koin.get()) {
            mainKaravel?.navigate(SplashPage())
        }
    }
}

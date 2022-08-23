package io.appoutlet.flux.desktop.feature.splash

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import io.appoutlet.flux.common.feature.splash.SplashViewModel
import io.appoutlet.flux.desktop.common.initialize
import io.appoutlet.flux.desktop.di.koin
import io.appoutlet.karavel.Page
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
class SplashPage : Page() {
    @Composable
    override fun content() {
        val viewModel: SplashViewModel = koin.get()
        viewModel.initialize()
        SplashView(viewModel = viewModel, karavel = karavel)
    }
}

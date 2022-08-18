package io.appoutlet.flux.desktop.feature.crateaccount

import androidx.compose.runtime.Composable
import io.appoutlet.flux.desktop.di.koin
import io.appoutlet.karavel.Page
import kotlinx.coroutines.FlowPreview

@FlowPreview
class CreateAccountPage : Page() {
    @Composable
    override fun content() {
        CreateAccountView(karavel = karavel, viewModel = koin.get())
    }
}

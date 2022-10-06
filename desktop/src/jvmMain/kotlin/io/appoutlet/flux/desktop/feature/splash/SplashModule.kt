package io.appoutlet.flux.desktop.feature.splash

import io.appoutlet.flux.common.feature.splash.SplashViewModel
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
val splashModule = module {
    factory { SplashViewModel(get(), get()) }
}

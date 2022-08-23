package io.appoutlet.flux.common.feature.splash

import org.koin.dsl.module

internal val splashModule = module {
    factory { SplashOrchestrator(get()) }
    factory { SplashUiStateMapper() }
}

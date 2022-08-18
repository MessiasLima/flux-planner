package io.appoutlet.flux.common.feature.signin

import org.koin.dsl.module

val signInModule = module {
    factory { SignInOrchestrator(get(), get()) }
}

package io.appoutlet.flux.desktop.feature.signin

import io.appoutlet.flux.common.feature.signin.SignInViewModel
import org.koin.dsl.module

val signInModule = module {
    factory { SignInViewModel(get()) }
}

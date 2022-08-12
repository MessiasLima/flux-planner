package io.appoutlet.flux.common.feature.signin

import io.appoutlet.flux.common.core.network.authentication.AuthenticationApi
import io.appoutlet.flux.common.domain.authentication.AuthenticationInteractor
import org.koin.dsl.module

val signInModule = module {
    factory { AuthenticationApi(get()) }
    factory { AuthenticationInteractor(get(), get(), get()) }
    factory { SignInOrchestrator(get(), get()) }
}

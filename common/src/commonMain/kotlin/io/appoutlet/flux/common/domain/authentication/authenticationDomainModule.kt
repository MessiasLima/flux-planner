package io.appoutlet.flux.common.domain.authentication

import org.koin.dsl.module

val authenticationDomainModule = module {
    factory { AuthenticationRequestMapper() }
    factory { AuthenticationInteractor(get(), get(), get()) }
}

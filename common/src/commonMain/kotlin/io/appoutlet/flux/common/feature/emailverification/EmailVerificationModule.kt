package io.appoutlet.flux.common.feature.emailverification

import org.koin.dsl.module

internal val emailVerificationModule = module {
    factory {
        EmailVerificationOrchestrator(
            userInteractor = get(),
            authenticationInteractor = get()
        )
    }
}

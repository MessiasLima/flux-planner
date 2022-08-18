package io.appoutlet.flux.common.feature.createaccount

import org.koin.dsl.module

val createAccountModule = module {
    factory { CreateAccountOrchestrator(get()) }
}

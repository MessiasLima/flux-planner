package io.appoutlet.flux.common.feature.createaccount

import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
val createAccountModule = module {
    factory { CreateAccountOrchestrator(get(), get()) }
}

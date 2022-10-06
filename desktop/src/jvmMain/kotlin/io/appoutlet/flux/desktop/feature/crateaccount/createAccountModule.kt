package io.appoutlet.flux.desktop.feature.crateaccount

import io.appoutlet.flux.common.feature.createaccount.CreateAccountViewModel
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
val createAccountModule = module {
    factory { CreateAccountViewModel(get()) }
}

package io.appoutlet.flux.desktop.feature.crateaccount

import io.appoutlet.flux.common.feature.createaccount.CreateAccountViewModel
import org.koin.dsl.module

val createAccountModule = module {
    factory { CreateAccountViewModel() }
}

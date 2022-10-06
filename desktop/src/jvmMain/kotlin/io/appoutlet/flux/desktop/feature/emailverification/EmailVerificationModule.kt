package io.appoutlet.flux.desktop.feature.emailverification

import io.appoutlet.flux.common.feature.emailverification.EmailVerificationViewModel
import org.koin.dsl.module

val emailVerificationModule = module {
    factory {
        EmailVerificationViewModel(orchestrator = get())
    }
}

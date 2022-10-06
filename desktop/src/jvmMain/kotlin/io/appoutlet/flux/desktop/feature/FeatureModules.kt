package io.appoutlet.flux.desktop.feature

import io.appoutlet.flux.desktop.feature.crateaccount.createAccountModule
import io.appoutlet.flux.desktop.feature.emailverification.emailVerificationModule
import io.appoutlet.flux.desktop.feature.signin.signInModule
import io.appoutlet.flux.desktop.feature.splash.splashModule
import kotlinx.coroutines.FlowPreview

@FlowPreview
val featureModules = arrayOf(
    signInModule,
    createAccountModule,
    splashModule,
    emailVerificationModule
)

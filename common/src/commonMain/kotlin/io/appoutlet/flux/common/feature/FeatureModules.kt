package io.appoutlet.flux.common.feature

import io.appoutlet.flux.common.feature.createaccount.createAccountModule
import io.appoutlet.flux.common.feature.signin.signInModule
import io.appoutlet.flux.common.feature.splash.splashModule
import kotlinx.coroutines.FlowPreview

@FlowPreview
val commonFeatureModules = arrayOf(
    signInModule,
    createAccountModule,
    splashModule,
)

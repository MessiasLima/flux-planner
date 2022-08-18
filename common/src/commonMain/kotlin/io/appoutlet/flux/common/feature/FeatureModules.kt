package io.appoutlet.flux.common.feature

import io.appoutlet.flux.common.feature.createaccount.createAccountModule
import io.appoutlet.flux.common.feature.signin.signInModule

val commonFeatureModules = arrayOf(
    signInModule,
    createAccountModule,
)

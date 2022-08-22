package io.appoutlet.flux.desktop.feature

import io.appoutlet.flux.desktop.feature.crateaccount.createAccountModule
import io.appoutlet.flux.desktop.feature.signin.signInModule

val featureModules = arrayOf(
    signInModule,
    createAccountModule,
)

package io.appoutlet.flux.common.core

import io.appoutlet.flux.common.core.database.databaseModule
import io.appoutlet.flux.common.core.database.databasePlatformModule
import io.appoutlet.flux.common.core.network.networkModule

val coreModules = arrayOf(
    networkModule,
    databaseModule,
    databasePlatformModule,
)
